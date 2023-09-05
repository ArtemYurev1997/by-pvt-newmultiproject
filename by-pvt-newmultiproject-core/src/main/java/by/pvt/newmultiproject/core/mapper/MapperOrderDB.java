package by.pvt.newmultiproject.core.mapper;


import by.pvt.newmultiproject.api.enums.Status;
import by.pvt.newmultiproject.core.domain.Order;


import java.sql.ResultSet;
import java.sql.SQLException;

public class MapperOrderDB {
    private final String id = "id";
    private final String userId = "user_id";
    private final String costs = "costs";
    private final String status = "status";

    public Order mapResultSetToOrder(ResultSet resultSet) {
        Order order = new Order();
        try{
            order.setId(resultSet.getLong(id));
            order.setUserId(Long.valueOf(resultSet.getString(userId)));
            order.setCost(Double.valueOf(resultSet.getString(costs)));
            String orderStatus = resultSet.getString(status);
            if(orderStatus.equals("Unformed")) {
                order.setStatus(Status.UNFORMED);
            }
            else if(orderStatus.equals("Waiting")) {
                order.setStatus(Status.WAITING_FOR_THE_COURIER);
            }
            else if(orderStatus.equals("On the way")) {
                order.setStatus(Status.ON_THE_WAY);
            }
            else {
                order.setStatus(Status.DONE);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
