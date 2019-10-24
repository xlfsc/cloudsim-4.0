package org.cloudbus.cloudsim.container.core.store.data.type;

public enum RowExistenceExpectation {
    IGNORE(0),//不做任何存在性检查
    EXPECT_EXIST(1),//期望行存在。如果该行存在，则操作成功；如果该行不存在，则操作失败
    EXPECT_NOT_EXIST(2);//期望行不存在。如果该行不存在，则操作成功；如果该行存在，则操作失败

    private int v;

    RowExistenceExpectation(int v) {
        this.v = v;
    }
}
