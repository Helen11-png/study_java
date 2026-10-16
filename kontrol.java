public class RestaurantDelivery implements CalculateOrderUseCase {
    @Override
    public double calculate(Order order) {
        double distance=order.get_distance();
        if (distance>1000) {
            throw new IllegalArgumentException("Ошибка"); //принудительный выброс непроверяемого исключения
        }
        return 120+(distance*0.5);
    }
}

public class Order {
    private String name;
    private Position from;
    private Position to;
    public Order(String name, Position from, Position to) {
        this.name=name;
        this.from=from;
        this.to=to;
    }
    public String get_name() {
        return name;
    }
    public Position get_from() {
        return from; }
    public Position get_to() {
        return to;
    }
    public double get_distance() {
        return Math.sqrt((to.get_x()-from.get_x())*(to.get_x()-from.get_x())+(to.get_y()-from.get_y())*(to.get_y()-from.get_y()));
    }
}

public class Position {  //https://gitverse.ru/blog/articles/development/456-chto-takoe-klassy-i-obuekty-v-java
    private double x;
    private double y;
    public Position(double x, double y){
        this.x=x;
        this.y=y;
    }
    public double get_x() {
        return x;
    }
    public double get_y() {
        return y;
    }

}

public interface CalculateOrderUseCase {
    double calculate(Order order);
}

public class Main {
    public static void main(String[] args) {
        Position a=new Position(0, 0);
        Position b=new Position(50, 50);
        Order order=new Order("заказ 1", a, b);
        CourierDelivery courier=new CourierDelivery();
        RestaurantDelivery restaurant=new RestaurantDelivery();
        ExpressDelivery express=new ExpressDelivery();
        System.out.println(courier.calculate(order));
        System.out.println(restaurant.calculate(order));
        System.out.println(express.calculate(order));
    }
}
public class ExpressDelivery implements CalculateOrderUseCase {
    @Override //https://itproger.com/spravka/java/override
    public double calculate(Order order) {
        double distance=order.get_distance();
        double n=distance;
        if (n<=0) {
            return 0;}
        return (n / 2) * (2 * 0.1 + 0.1 * (n - 1));
    }
}