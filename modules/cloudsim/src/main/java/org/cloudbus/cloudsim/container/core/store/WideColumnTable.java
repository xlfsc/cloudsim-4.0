package org.cloudbus.cloudsim.container.core.store;

import org.cloudbus.cloudsim.container.core.store.data.type.*;

import java.util.List;

public class WideColumnTable<K> {

    class Partition {
        private K partitionKey;
        private List<WideColumnRow> rows;
    }

    private String table_name;
    List<Partition> partitions;

    /**
     * 读取单行数据
     *
     * @param table_name     要读取的数据所在的表名
     * @param primary_key    该行全部的主键列，包含主键名和主键值，由Plainbuffer
     * @param columns_to_get 需要返回的全部列的列名。若为空，则返回该行的所有列。
     *                       如果指定的列不存在，则不会返回该列的数据。
     *                       如果给出了重复的列名，返回结果只会包含一次该列。
     *                       columns_to_get 中 string 的个数不应超过 128 个
     * @param time_range     读取数据的版本时间戳范围。
     *                       时间戳的取值最小值为0，最大值为INT64.MAX。
     *                       若要查询一个范围，则指定start_time和end_time。
     *                       若要查询一个特定时间戳，则指定specific_time。
     *                       例子：如果指定的time_range为(100, 200)，则返回的列数据的时间戳必须位于(100, 200)范围内，前闭后开区间
     * @param max_versions   读取数据时，返回的最多版本个数。
     *                       例子：如果指定max_versions为2，则每一列最多返回2个版本的数据。
     * @param filter         过滤条件表达式。
     *                       Filter经过protobuf序列化后的二进制数据。
     * @param start_column   指定读取时的起始列，主要用于宽行读。
     *                       返回的结果中包含当前起始列。
     *                       列的顺序按照列名的字典序排序。
     *                       例子：如果一张表有a、b、c三列，读取时指定start_column为b，则会从b列开始读，返回b、c两列。
     * @param end_column     指定读取时的结束列，主要用于宽行读。
     *                       返回的结果中不包含当前结束列。
     *                       列的顺序按照列名的字典序排序。
     *                       例子：如果一张表有a、b、c三列，读取时指定end_column为b，则读到b列时会结束，返回a列。
     * @return 读取到的数据，由Plainbuffer编码。如果该行不存在，则数据为空。
     */
    public Response getRow(String table_name, byte[] primary_key, String columns_to_get, TimeRange time_range
            , int max_versions, byte[] filter, String start_column, String end_column) {
        return null;
    }

    /**
     * 新插入一行，如果该行内容已经存在，则先删除旧行，再写入新行
     *
     * @param table_name     请求写入数据的表名
     * @param row            写入的行数据， 包括主键和属性列，Plainbuffer格式，编码详见Plainbuffer编码
     * @param condition      在数据写入前是否进行行存在性检查，可以取下面三个值：
     *                       IGNORE 表示不做行存在性检查。
     *                       EXPECT_EXIST 表示期望行存在。
     *                       EXPECT_NOT_EXIST 表示期望行不存在
     * @param return_content 写入成功后返回的数据类型，目前仅支持返回主键，主要用于主键列自增功能中
     * @return 当设置了return_content后，返回的数据。如果没设置return_content或者没返回数据，此处为NULL。Plainbuffer格式，编码详见Plainbuffer编码。
     */
    public Response putRow(String table_name, byte[] row, Condition condition, ReturnContent return_content) {
        return null;
    }

    /**
     * 更新一行，应用可以增加、删除一行中的属性列，或者更新已经存在的属性列的值。如果该行不存在，则新增一行
     *
     * @param row
     */
    /**
     * 更新一行，应用可以增加、删除一行中的属性列，或者更新已经存在的属性列的值。如果该行不存在，则新增一行
     *
     * @param table_name     请求更新数据的表名
     * @param row_change     更新的数据，包括主键和属性列，Plainbuffer格式，编码详见Plainbuffer编码。
     *                       该行本次想要更新的全部属性列，表格存储会根据 row_change中UpdateType的内容在这一行中新增、修改或者删除指定列的值。
     *                       该行已经存在的不在 row_change 中的列将不受影响。
     *                       UpdateType 可以取下面的值：
     *                       PUT：此时value 必须为有效的属性列值。语意为如果该列不存在，则新增一列；如果该列存在，则覆盖该列。
     *                       DELETE：此时该 value 必须为空，需要指定timestamp。语意为删除该列特定版本的数据。
     *                       DELETE_ALL：此时该 value 和timestamp 都必须为空。语意为删除该列所有版本的数据。
     * @param condition      在数据更新前是否进行存在性检查，可以取下面两个值：
     *                       IGNORE 表示不做行存在性检查。
     *                       EXPECT_EXIST 表示期望行存在。
     *                       若期待该行存在但该行不存在，则本次更新操作会失败, 返回错误；若忽视该行是否存在，则无论该行是否存在，都不会因此导致本次操作失败。
     * @param return_content 写入成功后返回的数据类型，目前仅支持返回主键，主要用于主键列自增功能中。
     * @return 当设置了return_content后，返回的数据。如果没设置return_content或者没返回值，此处为NULL。Plainbuffer格式，编码详见Plainbuffer编码。
     */
    public Response updateRow(String table_name, byte[] row_change, Condition condition, ReturnContent return_content) {
        return null;
    }

    /**
     * 删除一行
     *
     * @param table_name     请求更新数据的表名。
     * @param primary_key    删除行的主键，Plainbuffer格式，编码详见Plainbuffer编码。
     * @param condition      在数据更新前是否进行存在性检查，可以取下面两个值：
     *                       IGNORE：表示不做行存在性检查。
     *                       EXPECT_EXIST：表示期望行存在。
     *                       若期待该行存在，但实际该行不存在，则本次删除操作会失败, 返回错误；若忽视该行是否存在，则无论该行实际是否存在，都不会因此导致操作失败。
     * @param return_content 写入成功后返回的数据类型。目前仅支持返回主键，主要用于主键列自增功能中
     * @return 当设置了return_content后，返回的数据。如果没有设置return_content或者没有返回值，此处为NULL。Plainbuffer格式，编码详见Plainbuffer编码
     */
    public Response deleteRow(String table_name, byte[] primary_key, Condition condition, ReturnContent return_content) {
        return null;
    }

    /**
     * 批量读取一个或多个表中的若干行数据。
     * BatchGetRow 操作可视为多个 GetRow 操作的集合，各个操作独立执行，独立返回结果，独立计算服务能力单元。
     * 与执行大量的 GetRow 操作相比，使用 BatchGetRow 操作可以有效减少请求的响应时间，提高数据的读取速率。
     *
     * @param tables 指定了需要要读取的行信息。
     *               若 tables 中出现了下述情况，则操作整体失败，返回错误。
     *               tables 中任一表不存在。
     *               tables 中任一表名不符合命名规则和数据类型。
     *               tables 中任一行未指定主键、主键名称不符合规范或者主键类型不正确。
     *               tables 中任一表的 columns_to_get 内的列名不符合命名规则和数据类型。
     *               tables 中包含同名的表。
     *               tables 中任一表内包含主键完全相同的行。
     *               所有 tables 中 RowInBatchGetRowRequest 的总个数超过 100 个。
     *               tables 中任一表内不包含任何 RowInBatchGetRowRequest。
     *               tables 中任一表的 columns_to_get 超过 128 列。
     * @return 对应了每个 table 下读取到的数据。
     * 响应消息中 TableInBatchGetRowResponse 对象的顺序与 BatchGetRowRequest 中的 TableInBatchGetRowRequest 对象的顺序相同；每个 TableInBatchGetRowResponse 下面的 RowInBatchGetRowResponse 对象的顺序与 TableInBatchGetRowRequest 下面的 RowInBatchGetRowRequest 相同。
     * 如果某行不存在或者某行在指定的 columns_to_get 下没有数据，仍然会在 TableInBatchGetRowResponse 中有一条对应的 RowInBatchGetRowResponse，但其 row 下面的 primary_key_columns 和 attribute_columns 将为空。
     * 若某行读取失败，该行所对应的 RowInBatchGetRowResponse 中 is_ok 将为 false，此时 row 将为空。
     */
    public TableInBatchGetRowResponse batchGetRow(TableInBatchGetRowRequest tables) {
        return null;
    }

    /**
     * 批量插入、修改或删除一个或多个表中的若干行数据。
     * BatchWriteRow 操作可视为多个 PutRow、UpdateRow、DeleteRow 操作的集合。各个操作独立执行，独立返回结果，独立计算服务能力单元。
     * 与执行大量的单行写操作相比，使用 BatchWriteRow 操作可以有效减少请求的响应时间，提高数据的写入速率。
     *
     * @param tables 指定了需要要执行写操作的行信息。
     *               以下情况都会返回整体错误：
     *               tables 中任一表不存在。
     *               tables 中包含同名的表。
     *               tables 中任一表名不符合命名规则和数据类型。
     *               tables 中任一行操作未指定主键、主键列名称不符合规范或者主键列类型不正确。
     *               tables 中任一属性列名称不符合命名规则和数据类型。
     *               tables 中任一行操作存在与主键列同名的属性列。
     *               tables 中任一主键列或者属性列的值大小超过使用限制。
     *               tables 中任一表中存在主键完全相同的请求。
     *               tables 中所有表总的行操作个数超过 200 个，或者其含有的总数据大小超过 4 M。
     *               tables 中任一表内没有包含行操作，则返回 OTSParameterInvalidException 的错误。
     *               tables 中任一 PutRowInBatchWriteRowRequest 包含的 Column 个数超过 1024 个。
     *               tables 中任一 UpdateRowInBatchWriteRowRequest 包含的 ColumnUpdate 个数超过 1024 个。
     * @return 对应了每个 table 下各操作的响应信息，包括是否成功执行，错误码和消耗的服务能力单元。
     * 响应消息中 TableInBatchWriteRowResponse 对象的顺序与 BatchWriteRowRequest 中的 TableInBatchWriteRowRequest 对象的顺序相同；每个 TableInBatchWriteRowRequest 中 put_rows、update_rows、delete_rows 包含的 RowInBatchWriteRowResponse 对象的顺序分别与 TableInBatchWriteRowRequest 中 put_rows、update_rows、delete_rows 包含的 PutRowInBatchWriteRowRequest、UpdateRowInBatchWriteRowRequest 和 DeleteRowInBatchWriteRowRequest 对象的顺序相同。
     * 若某行读取失败，该行所对应的 RowInBatchWriteRowResponse 中 is_ok 将为 false。
     */
    public TableInBatchGetRowResponse batchWriteRow(TableInBatchGetRowRequest tables) {
        return null;
    }

    /**
     * 读取表中一个范围内的数据
     *
     * @return
     */
    public Response getRange(String table_name, Direction direction, String columns_to_get, TimeRange time_range
            , int max_versions, int limit, byte[] inclusive_start_primary_key, byte[] exclusive_end_primary_key
            , byte[] filter, String start_column, String end_column) {
        return null;
    }


}
