package com.xiaohe.test;


import java.lang.String;
import org.rocksdb.*;
import org.rocksdb.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : 小何
 * @Description :
 * @date : 2024-01-17 23:10
 */
public class RocksDBExample {
    private static final String dbPath = "./data/";
    private static final String cfdbPath = "./data-cf/";
    private static final String txdbPath = "./data-tx/";

    /**
     * 测试RocksDB的基本使用
     */
    public static void testBasicOperate() throws RocksDBException {
        System.out.println("开始测试RocksDB的基本操作");
        final Options options = new Options();
        final Filter bloomFilter = new BloomFilter(10);
        final ReadOptions readOptions = new ReadOptions().setFillCache(false);
        final Statistics statistics = new Statistics();
        final RateLimiter rateLimiter = new RateLimiter(10000000, 10000, 10);

        options.setCreateIfMissing(true)
                .setStatistics(statistics)
                .setWriteBufferSize(8 * SizeUnit.KB)
                .setMaxWriteBufferNumber(3)
                .setMaxBackgroundJobs(10)
                .setCompressionType(CompressionType.SNAPPY_COMPRESSION)
                .setCompactionStyle(CompactionStyle.UNIVERSAL);

        final BlockBasedTableConfig table_options = new BlockBasedTableConfig();
        Cache cache = new LRUCache(64 * 1024, 6);
        table_options.setBlockCache(cache)
                .setFilterPolicy(bloomFilter)
                .setBlockSizeDeviation(5)
                .setBlockRestartInterval(10)
                .setCacheIndexAndFilterBlocks(true)
                .setBlockCacheCompressed(new LRUCache(64 * 1000, 10));

        options.setTableFormatConfig(table_options);
        options.setRateLimiter(rateLimiter);

        try (final RocksDB db = RocksDB.open(options, dbPath)) {
            // 存储下面存放到 rocksdb 中的所有 key
            List<byte[]> keys = new ArrayList<>();
            keys.add("hello".getBytes());

            // 最简单的读写测试
            db.put("hello".getBytes(), "world".getBytes());
            byte[] value = db.get("hello".getBytes());
            System.out.println("key=hello, value=" + new String(value));

            // 批量写入，需要借助 WriteOptions + WriteBatch，将要写入的 key-value 放入 writeBatch 中
            // 使用 rocksdb.write(writeOptions, writeBatch)
            try (final WriteOptions writeOptions = new WriteOptions()) {
                for (int i = 1; i <= 9; i++) {
                    try (final WriteBatch batch = new WriteBatch()) {
                        for (int j = 1; j <= 9; j++) {
                            String k = i + "*" + j;
                            String v = (i * j) + "";
                            batch.put(k.getBytes(), v.getBytes());
                            keys.add(k.getBytes());
                        }
                        db.write(writeOptions, batch);
                    }
                }
            }

            // 批量获取，传入List，List的所有元素都为key，将RocksDB中符合条件的value找出来
            List<byte[]> values = db.multiGetAsList(keys);
            for (int i = 0; i < keys.size(); i++) {
                System.out.println(new String(keys.get(i)) + " " + new String(values.get(i)));
            }

            // 以迭代器方式遍历RocksDB的所有内容
            RocksIterator iterator = db.newIterator();
            for (iterator.seekToFirst(); iterator.isValid(); iterator.next()) {
                System.out.println(new String(iterator.key()) + " " + new String(iterator.value()));
            }
        }
    }


    public static void testCustomColumnFamily() {
        System.out.println("测试列族的使用");
        try (final ColumnFamilyOptions cfOptions = new ColumnFamilyOptions().optimizeLevelStyleCompaction()) {
            String cfName = "cf";
            final List<ColumnFamilyDescriptor> cfDescriptors = Arrays.asList(
                    new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY, cfOptions),
                    new ColumnFamilyDescriptor(cfName.getBytes(), cfOptions)
            );
            List<ColumnFamilyHandle> cfHandlers = new ArrayList<>();
            try (final DBOptions dbOptions = new DBOptions().setCreateIfMissing(true).setCreateMissingColumnFamilies(true)) {
                RocksDB db = RocksDB.open(dbOptions, cfdbPath, cfDescriptors, cfHandlers);
                ColumnFamilyHandle cfHandler = cfHandlers.stream().filter(x -> {
                    try {
                        String name = new String(x.getName());
                        return name.equals(cfName);
                    } catch (RocksDBException e) {
                        return false;
                    }
                }).collect(Collectors.toList()).get(0);

                try {
                    // 写入后，数据为
                    // 列族0 default : key2-value2
                    // 列族1 cf : key1-value1  key3-value3
                    db.put(cfHandlers.get(1), new WriteOptions(), "key".getBytes(), "value".getBytes());
                    try (final WriteBatch wb = new WriteBatch()) {
                        wb.put(cfHandlers.get(0), "key2".getBytes(), "value2".getBytes());
                        wb.put(cfHandlers.get(1), "key3".getBytes(), "value3".getBytes());
                        db.write(new WriteOptions(), wb);
                    }

                    // db.newIterator如果不传入 ColumnFamilyHandler，则获取默认列族

                    RocksIterator iterator0 = db.newIterator(cfHandlers.get(0));
                    for (iterator0.seekToFirst(); iterator0.isValid(); iterator0.next()) {
                        String key = new String(iterator0.key());
                        String value = new String(iterator0.value());
                        System.out.println("列族" + cfHandlers.get(0).getName() + key + " - " + value);
                    }
                    // 获取列族1的迭代器
                    RocksIterator iterator1 = db.newIterator(cfHandlers.get(1));
                    for (iterator1.seekToFirst(); iterator1.isValid(); iterator1.next()) {
                        String key = new String(iterator1.key());
                        String value = new String(iterator1.value());
                        System.out.println("列族" + cfHandlers.get(1).getName() + key + " - " + value);
                    }

                    // 删除创建的列族
                    db.dropColumnFamily(cfHandlers.get(1));
                } finally {
                    for (final ColumnFamilyHandle handle : cfHandlers) {
                        handle.close();
                    }
                }
            } catch (RocksDBException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        testCustomColumnFamily();
    }
}
