package tese1;

public class Transaction {

    private final double amount;
    private final long timestamp;

    // 新增的 V 字段
    private final double v1;
    private final double v2;
    private final double v3;
    private final double v4;
    private final double v5;
    private final double v6;
    private final double v7;
    private final double v8;
    private final double v9;
    private final double v10;
    private final double v11;
    private final double v12;
    private final double v13;
    private final double v14;
    private final double v15;
    private final double v16;
    private final double v17;
    private final double v18;
    private final double v19;
    private final double v20;
    private final double v21;
    private final double v22;
    private final double v23;
    private final double v24;
    private final double v25;
    private final double v26;
    private final double v27;
    private final double v28;

    // 修改后的构造函数
    public Transaction(  long timestamp,
                       double v1, double v2, double v3, double v4, double v5,
                       double v6, double v7, double v8, double v9, double v10,
                       double v11, double v12, double v13, double v14, double v15,
                       double v16, double v17, double v18, double v19, double v20,
                       double v21, double v22, double v23, double v24, double v25,
                       double v26, double v27, double v28,double amount) {
        this.timestamp = timestamp;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.v5 = v5;
        this.v6 = v6;
        this.v7 = v7;
        this.v8 = v8;
        this.v9 = v9;
        this.v10 = v10;
        this.v11 = v11;
        this.v12 = v12;
        this.v13 = v13;
        this.v14 = v14;
        this.v15 = v15;
        this.v16 = v16;
        this.v17 = v17;
        this.v18 = v18;
        this.v19 = v19;
        this.v20 = v20;
        this.v21 = v21;
        this.v22 = v22;
        this.v23 = v23;
        this.v24 = v24;
        this.v25 = v25;
        this.v26 = v26;
        this.v27 = v27;
        this.v28 = v28;
        this.amount = amount;
    }

    // Getter 方法


    public double getAmount() {
        return amount;
    }

    public double getV1() {
        return v1;
    }

    public double getV2() {
        return v2;
    }

    public double getV3() {
        return v3;
    }

    public double getV4() {
        return v4;
    }

    public double getV5() {
        return v5;
    }

    public double getV6() {
        return v6;
    }

    public double getV7() {
        return v7;
    }

    public double getV8() {
        return v8;
    }

    public double getV9() {
        return v9;
    }

    public double getV10() {
        return v10;
    }

    public double getV11() {
        return v11;
    }

    public double getV12() {
        return v12;
    }

    public double getV13() {
        return v13;
    }

    public double getV14() {
        return v14;
    }

    public double getV15() {
        return v15;
    }

    public double getV16() {
        return v16;
    }

    public double getV17() {
        return v17;
    }

    public double getV18() {
        return v18;
    }

    public double getV19() {
        return v19;
    }

    public double getV20() {
        return v20;
    }

    public double getV21() {
        return v21;
    }

    public double getV22() {
        return v22;
    }

    public double getV23() {
        return v23;
    }

    public double getV24() {
        return v24;
    }

    public double getV25() {
        return v25;
    }

    public double getV26() {
        return v26;
    }

    public double getV27() {
        return v27;
    }

    public double getV28() {
        return v28;
    }

    public long getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "{" +
                "\"Time\":" + timestamp + "," +
                "\"V1\":" + v1 + "," +
                "\"V2\":" + v2 + "," +
                "\"V3\":" + v3 + "," +
                "\"V4\":" + v4 + "," +
                "\"V5\":" + v5 + "," +
                "\"V6\":" + v6 + "," +
                "\"V7\":" + v7 + "," +
                "\"V8\":" + v8 + "," +
                "\"V9\":" + v9 + "," +
                "\"V10\":" + v10 + "," +
                "\"V11\":" + v11 + "," +
                "\"V12\":" + v12 + "," +
                "\"V13\":" + v13 + "," +
                "\"V14\":" + v14 + "," +
                "\"V15\":" + v15 + "," +
                "\"V16\":" + v16 + "," +
                "\"V17\":" + v17 + "," +
                "\"V18\":" + v18 + "," +
                "\"V19\":" + v19 + "," +
                "\"V20\":" + v20 + "," +
                "\"V21\":" + v21 + "," +
                "\"V22\":" + v22 + "," +
                "\"V23\":" + v23 + "," +
                "\"V24\":" + v24 + "," +
                "\"V25\":" + v25 + "," +
                "\"V26\":" + v26 + "," +
                "\"V27\":" + v27 + "," +
                "\"V28\":" + v28 + "," +
                "\"Amount\":" + amount +
                "}";
    }
}