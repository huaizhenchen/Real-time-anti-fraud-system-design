
package tese1;

        import org.apache.flink.api.common.functions.FilterFunction;
        import org.apache.flink.api.common.functions.MapFunction;
        import org.apache.flink.streaming.api.datastream.DataStream;
        import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
        import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
        import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
        import org.apache.flink.streaming.api.windowing.time.Time;
        import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
        import org.apache.flink.util.Collector;
        import org.apache.flink.api.common.eventtime.WatermarkStrategy;
        import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
        import java.time.Duration;
        import org.apache.http.HttpResponse;
        import org.apache.http.HttpStatus;
        import org.apache.http.client.methods.HttpPost;
        import org.apache.http.entity.StringEntity;
        import org.apache.http.impl.client.CloseableHttpClient;
        import org.apache.http.impl.client.HttpClients;
        import org.apache.http.util.EntityUtils;
        import org.json.JSONObject;

        public class frund2 {

            public static void main(String[] args) throws Exception {
                StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

                DataStream<String> rawTransactions = env.socketTextStream("localhost", 9999);

                DataStream<Transaction> transactions = rawTransactions.map(new MapFunction<String, Transaction>() {
                    @Override
                    public Transaction map(String value) throws Exception {
                        String[] parts = value.split("\\t");
                        if (parts.length != 30) {
                            System.err.println("Invalid transaction format: " + value);
                            return null;
                        }
                        Transaction transaction = new Transaction(
                                 // amount
                                Long.parseLong(parts[0]),// time
                                Double.parseDouble(parts[1]),
                                Double.parseDouble(parts[2]), // V1
                                Double.parseDouble(parts[3]), // V2
                                Double.parseDouble(parts[4]),
                                Double.parseDouble(parts[5]),
                                Double.parseDouble(parts[6]),
                                Double.parseDouble(parts[7]),
                                Double.parseDouble(parts[8]),
                                Double.parseDouble(parts[9]),
                                Double.parseDouble(parts[10]),
                                Double.parseDouble(parts[11]),
                                Double.parseDouble(parts[12]),
                                Double.parseDouble(parts[13]),
                                Double.parseDouble(parts[14]),
                                Double.parseDouble(parts[15]),
                                Double.parseDouble(parts[16]),
                                Double.parseDouble(parts[17]),
                                Double.parseDouble(parts[18]),
                                Double.parseDouble(parts[19]),
                                Double.parseDouble(parts[20]),
                                Double.parseDouble(parts[21]),
                                Double.parseDouble(parts[22]),
                                Double.parseDouble(parts[23]),
                                Double.parseDouble(parts[24]),
                                Double.parseDouble(parts[25]),
                                Double.parseDouble(parts[26]),
                                Double.parseDouble(parts[27]),
                                Double.parseDouble(parts[28]),// V28
                                Double.parseDouble(parts[29])
                        );
                        return transaction;
                    }
                        })
                        .filter(new FilterFunction<Transaction>() {
                            @Override
                            public boolean filter(Transaction transaction) throws Exception {
                                return transaction != null;
                            }
                        });

                // Assign timestamps and watermarks
                DataStream<Transaction> timestampedTransactions = transactions.assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Transaction>forBoundedOutOfOrderness(Duration.ofSeconds(5))
                                .withTimestampAssigner(new SerializableTimestampAssigner<Transaction>() {
                                    @Override
                                    public long extractTimestamp(Transaction transaction, long recordTimestamp) {
                                        return transaction.getTimestamp(); // assuming this is in milliseconds
                                    }
                                }));
                System.out.println(timestampedTransactions);
                transactions
                        .keyBy(Transaction::getAmount)
                        .window(TumblingProcessingTimeWindows.of(Time.seconds(5)))
                        .process(new FraudDetector())
                        .print();

                env.execute("Fraud Detection Job with Time Window");
            }
            public static class FraudDetector extends ProcessWindowFunction<Transaction, String, Double, TimeWindow> {
                @Override
                public void process(Double key, Context context, Iterable<Transaction> elements, Collector<String> out) throws Exception {
                    CloseableHttpClient httpClient = HttpClients.createDefault();

                    try {
                        for (Transaction transaction : elements) {
                            JSONObject json = new JSONObject();
                            json.put("Time", transaction.getTimestamp());
                            json.put("V1", transaction.getV1());
                            json.put("V2", transaction.getV2());
                            json.put("V3", transaction.getV3());
                            json.put("V4", transaction.getV4());
                            json.put("V5", transaction.getV5());
                            json.put("V6", transaction.getV6());
                            json.put("V7", transaction.getV7());
                            json.put("V8", transaction.getV8());
                            json.put("V9", transaction.getV9());
                            json.put("V10", transaction.getV10());
                            json.put("V11", transaction.getV11());
                            json.put("V12", transaction.getV12());
                            json.put("V13", transaction.getV13());
                            json.put("V14", transaction.getV14());
                            json.put("V15", transaction.getV15());
                            json.put("V16", transaction.getV16());
                            json.put("V17", transaction.getV17());
                            json.put("V18", transaction.getV18());
                            json.put("V19", transaction.getV19());
                            json.put("V20", transaction.getV20());
                            json.put("V21", transaction.getV21());
                            json.put("V22", transaction.getV22());
                            json.put("V23", transaction.getV23());
                            json.put("V24", transaction.getV24());
                            json.put("V25", transaction.getV25());
                            json.put("V26", transaction.getV26());
                            json.put("V27", transaction.getV27());
                            json.put("V28", transaction.getV28());
                            json.put("Amount", transaction.getAmount());

                            HttpPost request = new HttpPost("http://127.0.0.1:5000/predict"); // Replace with actual IP if needed
                            StringEntity params = new StringEntity(json.toString());
                            request.addHeader("content-type", "application/json");
                            request.setEntity(params);
                            HttpResponse response = httpClient.execute(request);

                            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                                // Log non-200 responses
                                System.out.println("Received non-200 response: " + response.getStatusLine().getStatusCode());
                            } else {
                                String jsonResponse = EntityUtils.toString(response.getEntity());
                                JSONObject responseObj = new JSONObject(jsonResponse);

                                boolean isFraud = responseObj.getJSONArray("prediction").getInt(0) == -1;
                                if (isFraud) {
                                    out.collect("Suspicious transaction detected: " + transaction);
                                }
                            }
                        }
                    } catch (Exception e) {
                        // Log exceptions
                        e.printStackTrace();
                    } finally {
                        httpClient.close();
                    }
                }
            }
        }

