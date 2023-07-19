package service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import vo.OrderListVO;
import vo.OrderVO;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class AlarmCenter {
    private static AlarmCenter instance = new AlarmCenter();
    private HashMap<Integer, AsyncContext> map = new HashMap<>();
    private HashMap<Integer, ArrayList<OrderVO>> waiting = new HashMap<>();

    private AlarmCenter() {

    }

    public static AlarmCenter getInstance() {
        return instance;
    }

    public void addStore(int storeno, AsyncContext context) {
        this.map.put(storeno, context);
        if (waiting.containsKey(storeno)) {
            ArrayList<OrderVO> orders = waiting.get(storeno);
            waiting.remove(storeno);
            for (OrderVO order: orders) {
                send(storeno, order);
            }
        }
    }

    public void send(int storeno, OrderVO order) {
        AsyncContext context = map.get(storeno);
        if (context == null) {
            addWaiting(storeno, order);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        try {
            System.out.println("in send: " + Thread.currentThread());
            PrintWriter writer = response.getWriter();
            writer.print("data: " + toJSON(order) + "\n\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            map.remove(storeno);
            addWaiting(storeno, order);
            return;
        }
    }

    private void addWaiting(int storeno, OrderVO order) {
        if (!waiting.containsKey(storeno)) {
            waiting.put(storeno, new ArrayList<>());
        }
        waiting.get(storeno).add(order);
    }

    private String toJSON(OrderVO order) {
        JSONObject orderJSON = new JSONObject();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
        orderJSON.put("no", order.getNo());
        orderJSON.put("price", order.getPrice());
        orderJSON.put("time", format.format(order.getOrderTime()));
        orderJSON.put("place", order.getPlace());
        JSONArray list = new JSONArray();
        for (OrderListVO menu : order.getMenuList()) {
            JSONObject menuJSON = new JSONObject();
            menuJSON.put("name", menu.getMenuName());
            menuJSON.put("price", menu.getPrice());
            menuJSON.put("quantity", menu.getQuantity());
            list.add(menuJSON);
        }
        orderJSON.put("list", list);
        return orderJSON.toJSONString();

    }
}
