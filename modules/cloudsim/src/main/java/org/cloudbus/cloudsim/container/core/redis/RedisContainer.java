package org.cloudbus.cloudsim.container.core.redis;

import com.oracle.jrockit.jfr.DataType;
import org.cloudbus.cloudsim.container.core.Container;
import org.cloudbus.cloudsim.container.schedulers.ContainerCloudletScheduler;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * https://gitee.com/whvse/RedisUtil
 */
public class RedisContainer extends Container {

    double accessTime;
    double responseTime;

    /**
     * Creates a new Container object.
     *
     * @param id
     * @param userId
     * @param mips
     * @param numberOfPes
     * @param ram
     * @param bw
     * @param size
     * @param containerManager
     * @param containerCloudletScheduler
     * @param schedulingInterval
     */
    public RedisContainer(int id, int userId, double mips, int numberOfPes, int ram, long bw, long size, String containerManager, ContainerCloudletScheduler containerCloudletScheduler, double schedulingInterval) {
        super(id, userId, mips, numberOfPes, ram, bw, size, containerManager, containerCloudletScheduler, schedulingInterval);
    }

    public double getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(double accessTime) {
        this.accessTime = accessTime;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public boolean accessRedis() {
        return false;
    }


    /**
     * key存在时删除key
     *
     * @param key
     */
    public void delete(String key) {
    }

    /**
     * 批量删除key
     *
     * @param keys
     */
    public void delete(Collection keys) {
    }

    /**
     * 序列化key，返回被序列化的值
     *
     * @param key
     */
    public byte[] dump(String key) {
        return null;
    }

    /**
     * 检查key是否存在
     *
     * @param key
     */
    public Boolean hasKey(String key) {
        return null;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return null;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param date
     */
    public Boolean expireAt(String key, Date date) {
        return null;
    }

    /**
     * 查找所有符合给定模式(pattern)的key
     *
     * @param pattern
     */
    public Set keys(String pattern) {
        return null;
    }

    /**
     * 将当前数据库的key移动到给定的数据库db当中
     *
     * @param key
     * @param dbIndex
     */
    public Boolean move(String key, int dbIndex) {
        return null;
    }

    /**
     * 移除key的过期时间，key将持久保持
     *
     * @param key
     */
    public Boolean persist(String key) {
        return null;
    }

    /**
     * 返回key的剩余的过期时间
     *
     * @param key
     * @param unit
     */
    public Long getExpire(String key, TimeUnit unit) {
        return null;
    }

    /**
     * 返回key的剩余的过期时间
     *
     * @param key
     */
    public Long getExpire(String key) {
        return null;
    }

    /**
     * 从当前数据库中随机返回一个key
     */
    public String randomKey() {
        return null;
    }

    /**
     * 修改key的名称
     *
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
    }

    /**
     * 仅当newkey不存在时，将oldKey改名为 newkey
     *
     * @param oldKey
     * @param newKey
     */
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return null;
    }

    /**
     * 返回key所储存的值的类型
     *
     * @param key
     */
    public DataType type(String key) {
        return null;
    }

    /**
     * 获取指定key的值
     *
     * @param key
     */
    public String get(String key) {
        return null;
    }

    /**
     * 返回key中字符串值的子字符
     *
     * @param key
     * @param start
     * @param end
     */
    public String getRange(String key, long start, long end) {
        return null;
    }

    /**
     * 将key的值设为value，并返回key旧值
     *
     * @param key
     * @param value
     */
    public String getAndSet(String key, String value) {
        return null;
    }

    /**
     * 对key所储存的值，获取指定位置上的bit
     *
     * @param key
     * @param offset
     */
    public Boolean getBit(String key, long offset) {
        return null;
    }

    /**
     * 批量获取
     *
     * @param keys
     */
    public List multiGet(Collection keys) {
        return null;
    }

    /**
     * 设置指定key的值
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
    }

    /**
     * 设置指定位置上的ASCII码
     *
     * @param key
     * @param offset
     * @param value
     */
    public boolean setBit(String key, long offset, boolean value) {
        return false;
    }

    /**
     * 将值value关联到key，并设置key过期时间
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
    }

    /**
     * 只有在 key 不存在时设置 key 的值
     *
     * @param key
     * @param value
     */
    public boolean setIfAbsent(String key, String value) {
        return false;
    }

    /**
     * 用value覆写key的值，从偏移量offset开始
     *
     * @param key
     * @param value
     * @param offset
     */
    public void setRange(String key, String value, long offset) {
    }

    /**
     * 批量添加
     *
     * @param maps
     */
    public void multiSet(Map<String, String> maps) {
    }

    /**
     * 批量添加，仅当所有key都不存在
     *
     * @param maps
     */
    public boolean multiSetIfAbsent(Map<String, String> maps) {
        return false;
    }

    /**
     * 追加到末尾
     *
     * @param key
     * @param value
     */
    public Integer append(String key, String value) {
        return null;
    }

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key
     * @param increment
     */
    public Long incrBy(String key, long increment) {
        return null;
    }

    /**
     * 增加(自增长), 负数则为自减
     *
     * @param key
     * @param increment
     */
    public Double incrByFloat(String key, double increment) {
        return null;
    }

    /**
     * 获取字符串的长度
     *
     * @param key
     */
    public Long size(String key) {
        return null;
    }

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     */
    public Object hGet(String key, String field) {
        return null;
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     */
    public Map hGetAll(String key) {
        return null;
    }

    /**
     * 获取所有给定字段的值
     *
     * @param key
     * @param fields
     */
    public List hMultiGet(String key, Collection fields) {
        return null;
    }

    /**
     * 添加字段
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public void hPut(String key, String hashKey, String value) {
    }

    /**
     * 添加多个字段
     *
     * @param key
     * @param maps
     */
    public void hPutAll(String key, Map maps) {
    }

    /**
     * 仅当hashKey不存在时才设置
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return null;
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key
     * @param fields
     */
    public Long hDelete(String key, Object... fields) {
        return null;
    }

    /**
     * 查看哈希表key中指定的字段是否存在
     *
     * @param key
     * @param field
     */
    public boolean hExists(String key, String field) {
        return false;
    }

    /**
     * 为哈希表key中指定字段的值增加increment
     *
     * @param key
     * @param field
     * @param increment
     */
    public Long hIncrBy(String key, Object field, long increment) {
        return null;
    }

    /**
     * 为哈希表key中指定字段的值增加increment
     *
     * @param key
     * @param field
     * @param delta
     */
    public Double hIncrByFloat(String key, Object field, double delta) {
        return null;
    }

    /**
     * 获取所有哈希表中的字段
     *
     * @param key
     */
    public Set hKeys(String key) {
        return null;
    }

    /**
     * 获取哈希表中字段的数量
     *
     * @param key
     */
    public Long hSize(String key) {
        return null;
    }

    /**
     * 获取哈希表中所有值
     *
     * @param key
     */
    public List hValues(String key) {
        return null;
    }

    /**
     * 迭代哈希表中的键值对
     *
     * @param key
     * @param options
     */
    public Cursor hScan(String key, ScanOptions options) {
        return null;
    }

    /**
     * 通过索引获取列表中的元素
     *
     * @param key
     * @param index
     */
    public String lIndex(String key, long index) {
        return null;
    }

    /**
     * 获取列表指定范围内的元素
     *
     * @param key
     * @param start
     * @param end
     */
    public List lRange(String key, long start, long end) {
        return null;
    }

    /**
     * 存储在list头部
     *
     * @param key
     * @param value
     */
    public Long lLeftPush(String key, String value) {
        return null;
    }

    /**
     * 存储在list头部
     *
     * @param key
     * @param value
     */
    public Long lLeftPushAll(String key, String... value) {
        return null;
    }

    /**
     * 存储在list头部
     *
     * @param key
     * @param value
     */
    public Long lLeftPushAll(String key, Collection value) {
        return null;
    }

    /**
     * 当list存在的时候才加入
     *
     * @param key
     * @param value
     */
    public Long lLeftPushIfPresent(String key, String value) {
        return null;
    }

    /**
     * 如果pivot存在,再pivot前面添加
     *
     * @param key
     * @param pivot
     * @param value
     */
    public Long lLeftPush(String key, String pivot, String value) {
        return null;
    }

    /**
     * 存储在list尾部
     *
     * @param key
     * @param value
     */
    public Long lRightPush(String key, String value) {
        return null;
    }

    /**
     * 存储在list尾部
     *
     * @param key
     * @param value
     */
    public Long lRightPushAll(String key, String... value) {
        return null;
    }

    /**
     * 存储在list尾部
     *
     * @param key
     * @param value
     */
    public Long lRightPushAll(String key, Collection value) {
        return null;
    }

    /**
     * 当list存在的时候才加入
     *
     * @param key
     * @param value
     */
    public Long lRightPushIfPresent(String key, String value) {
        return null;
    }

    /**
     * 在pivot元素的右边添加值
     *
     * @param key
     * @param pivot
     * @param value
     */
    public Long lRightPush(String key, String pivot, String value) {
        return null;
    }

    /**
     * 通过索引设置列表元素的值
     *
     * @param key
     * @param index
     * @param value
     */
    public void lSet(String key, long index, String value) {
    }

    /**
     * 移出并获取列表的第一个元素
     *
     * @param key
     */
    public String lLeftPop(String key) {
        return null;
    }

    /**
     * 移出并获取第一个元素,没有则阻塞直到超时或有为止
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public String lBLeftPop(String key, long timeout, TimeUnit unit) {
        return null;
    }

    /**
     * 移除并获取列表最后一个元素
     *
     * @param key
     */
    public String lRightPop(String key) {
        return null;
    }

    /**
     * 移出并获取最后个元素,没有则阻塞直到超时或有为止
     *
     * @param key
     * @param timeout
     * @param unit
     */
    public String lBRightPop(String key, long timeout, TimeUnit unit) {
        return null;
    }

    /**
     * 移除最后一个元素并加到另一个列表并返回
     *
     * @param sKey
     * @param dKey
     */
    public String lRightPopAndLeftPush(String sKey, String dKey) {
        return null;
    }

    /**
     * 移除最后个元素并加到另个列表并返回,阻塞超时或有
     *
     * @param sKey
     * @param dKey
     * @param timeout
     * @param unit
     */
    public String lBRightPopAndLeftPush(String sKey, String dKey, long timeout, TimeUnit unit) {
        return null;
    }

    /**
     * 删除集合中值等于value得元素
     *
     * @param key
     * @param index
     * @param value
     */
    public Long lRemove(String key, long index, String value) {
        return null;
    }

    /**
     * 裁剪list
     *
     * @param key
     * @param start
     * @param end
     */
    public void lTrim(String key, long start, long end) {
    }

    /**
     * 获取列表长度
     *
     * @param key
     */
    public Long lLen(String key) {
        return null;
    }

    /**
     * 获取集合所有元素
     *
     * @param key
     */
    public Set sMembers(String key) {
        return null;
    }

    /**
     * 获取集合大小
     *
     * @param key
     */
    public Long sSize(String key) {
        return null;
    }

    /**
     * 判断集合是否包含value
     *
     * @param key
     * @param value
     */
    public Boolean sIsMember(String key, Object value) {
        return null;
    }

    /**
     * 随机获取集合中的一个元素
     *
     * @param key
     */
    public String sRandomMember(String key) {
        return null;
    }

    /**
     * 随机获取集合count个元素
     *
     * @param key
     * @param count
     */
    public List sRandomMembers(String key, long count) {
        return null;
    }

    /**
     * 随机获取count个元素并去除重复的
     *
     * @param key
     * @param count
     */
    public Set sDistinctRandomMembers(String key, long count) {
        return null;
    }

    /**
     * 使用迭代器获取元素
     *
     * @param key
     * @param options
     */
    public Cursor sScan(String key, ScanOptions options) {
        return null;
    }

    /**
     * 获取两个集合的交集
     *
     * @param key
     * @param otherKey
     */
    public Set sIntersect(String key, String otherKey) {
        return null;
    }

    /**
     * 获取key集合与多个集合的交集
     *
     * @param key
     * @param otherKeys
     */
    public Set sIntersect(String key, Collection otherKeys) {
        return null;
    }

    /**
     * key集合与oKey的交集存储到dKey中
     *
     * @param key
     * @param oKey
     * @param dKey
     */
    public Long sIntersectAndStore(String key, String oKey, String dKey) {
        return null;
    }

    /**
     * key与多个集合的交集存储到dKey中
     *
     * @param key
     * @param oKeys
     * @param dKey
     */
    public Long sIntersectAndStore(String key, Collection oKeys, String dKey) {
        return null;
    }

    /**
     * 获取两个集合的并集
     *
     * @param key
     * @param otherKeys
     */
    public Set sUnion(String key, String otherKeys) {
        return null;
    }

    /**
     * 获取key集合与多个集合的并集
     *
     * @param key
     * @param otherKeys
     */
    public Set sUnion(String key, Collection otherKeys) {
        return null;
    }

    /**
     * key集合与oKey的并集存储到dKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     */
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return null;
    }

    /**
     * key与多个集合的并集存储到dKey中
     *
     * @param key
     * @param oKeys
     * @param dKey
     */
    public Long sUnionAndStore(String key, Collection oKeys, String dKey) {
        return null;
    }

    /**
     * 获取两个集合的差集
     *
     * @param key
     * @param otherKey
     */
    public Set sDifference(String key, String otherKey) {
        return null;
    }

    /**
     * 获取key集合与多个集合的差集
     *
     * @param key
     * @param otherKeys
     */
    public Set sDifference(String key, Collection otherKeys) {
        return null;
    }

    /**
     * key与oKey集合的差集存储到dKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     */
    public Long sDifference(String key, String otherKey, String destKey) {
        return null;
    }

    /**
     * key与多个集合的差集存储到dKey中
     *
     * @param key
     * @param otherKeys
     * @param dKey
     */
    public Long sDifference(String key, Collection otherKeys, String dKey) {
        return null;
    }

    /**
     * 添加
     *
     * @param key
     * @param values
     */
    public Long sAdd(String key, String... values) {
        return null;
    }

    /**
     * 移除
     *
     * @param key
     * @param values
     */
    public Long sRemove(String key, Object... values) {
        return null;
    }

    /**
     * 随机移除一个元素
     *
     * @param key
     */
    public String sPop(String key) {
        return null;
    }

    /**
     * 将key集合中value移到destKey中
     *
     * @param key
     * @param value
     * @param destKey
     */
    public Boolean sMove(String key, String value, String destKey) {
        return null;
    }

    /**
     * 获取元素,小到大排序,s开始e结束位置
     *
     * @param key
     * @param start
     * @param end
     */
    public Set zRange(String key, long start, long end) {
        return null;
    }

    /**
     * 获取集合元素, 并且把score值也获取
     *
     * @param key
     * @param start
     * @param end
     */
    public Set<TypedTuple> zRangeWithScores(String key, long start, long end) {
        return null;
    }

    /**
     * 根据score范围查询元素,从小到大排序
     *
     * @param key
     * @param min
     * @param max
     */
    public Set zRangeByScore(String key, double min, double max) {
        return null;
    }

    /**
     * 根据score范围查询元素,并返回score
     *
     * @param key
     * @param min
     * @param max
     */
    public Set<TypedTuple> zRangeByScoreWithScores(String key, double min, double max) {
        return null;
    }

    /**
     * 根据score查询元素,s开始e结束位置
     *
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     */
    public Set zRangeByScoreWithScores(String key, double min, long max, long start, long end) {
        return null;
    }

    /**
     * 获取集合元素, 从大到小排序
     *
     * @param key
     * @param start
     * @param end
     */
    public Set zReverseRange(String key, long start, long end) {
        return null;
    }

    /**
     * 获取元素,从大到小排序,并返回score
     *
     * @param key
     * @param start
     * @param end
     */
    public Set<TypedTuple> zReverseRangeWithScores(String key, long start, long end) {
        return null;
    }

    /**
     * 根据score范围查询元素,从大到小排序
     *
     * @param key
     * @param min
     * @param max
     */
    public Set zReverseRangeByScore(String key, double min, double max) {
        return null;
    }

    /**
     * 根据score查询,大到小排序返回score
     *
     * @param key
     * @param min
     * @param max
     */
    public Set zReverseRangeByScoreWithScores(String key, double min, double max) {
        return null;
    }

    /**
     * 根据score查询,大到小,s开始e结束
     *
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     */
    public Set zReverseRangeByScore(String key, double min, long max, long start, long end) {
        return null;
    }

    /**
     * 返回元素在集合的排名,score由小到大
     *
     * @param key
     * @param value
     */
    public Long zRank(String key, Object value) {
        return null;
    }

    /**
     * 返回元素在集合的排名,score由大到小
     *
     * @param key
     * @param value
     */
    public Long zReverseRank(String key, Object value) {
        return null;
    }

    /**
     * 根据score值范围获取集合元素的数量
     *
     * @param key
     * @param min
     * @param max
     */
    public Long zCount(String key, double min, double max) {
        return null;
    }

    /**
     * 获取集合大小
     *
     * @param key
     */
    public Long zSize(String key) {
        return null;
    }

    /**
     * 获取集合大小
     *
     * @param key
     */
    public Long zZCard(String key) {
        return null;
    }

    /**
     * 获取集合中value元素的score值
     *
     * @param key
     * @param value
     */
    public Double zScore(String key, Object value) {
        return null;
    }

    /**
     * 获取key和oKey的并集并存储在dKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     */
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return null;
    }

    /**
     * 获取key和多个集合并集并存在dKey中
     *
     * @param key
     * @param otherKeys
     * @param dKey
     */
    public Long zUnionAndStore(String key, Collection otherKeys, String dKey) {
        return null;
    }

    /**
     * 获取key和oKey交集并存在destKey中
     *
     * @param key
     * @param otherKey
     * @param destKey
     */
    public Long zIntersectAndStore(String key, String otherKey, String destKey) {
        return null;
    }

    /**
     * 获取key和多个集合交集并存在dKey中
     *
     * @param key
     * @param oKeys
     * @param dKey
     */
    public Long zIntersectAndStore(String key, Collection oKeys, String dKey) {
        return null;
    }

    /**
     * 使用迭代器获取
     *
     * @param key
     * @param options
     */
    public Cursor<TypedTuple> zScan(String key, ScanOptions options) {
        return null;
    }

    /**
     * 添加元素,zSet按score由小到大排列
     *
     * @param key
     * @param value
     * @param score
     */
    public Boolean zAdd(String key, String value, double score) {
        return null;
    }

    /**
     * 批量添加,TypedTuple使用见下面介绍
     *
     * @param key
     * @param values
     */
    public Long zAdd(String key, Set<TypedTuple> values) {
        return null;
    }

    /**
     * 移除
     *
     * @param key
     * @param values
     */
    public Long zRemove(String key, Object... values) {
        return null;
    }

    /**
     * 增加元素的score值,并返回增加后的值
     *
     * @param key
     * @param value
     * @param delta
     */
    public Double zIncrementScore(String key, String value, double delta) {
        return null;
    }

    /**
     * 移除指定索引位置的成员
     *
     * @param key
     * @param start
     * @param end
     */
    public Long zRemoveRange(String key, long start, long end) {
        return null;
    }

    /**
     * 根据指定的score值的范围来移除成员
     *
     * @param key
     * @param min
     * @param max
     */
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return null;
    }
}
