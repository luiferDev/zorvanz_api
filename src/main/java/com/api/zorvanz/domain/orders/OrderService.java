package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.cart.Cart;
import com.api.zorvanz.domain.cart.CartRepository;
import com.api.zorvanz.domain.customer.Customer;
import com.api.zorvanz.strategy.concrete.CashConcrete;
import com.api.zorvanz.strategy.context.PaymentContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final OrdersRepository ordersRepository;
    private final CartRepository cartRepository;

    public OrderService ( OrdersRepository ordersRepository,
                          CartRepository cartRepository ) {
        this.ordersRepository = ordersRepository;
        this.cartRepository = cartRepository;
    }
    // debería recibir id del carrito y bajar toda la info del carrito
    @Override
    public OrderData createOrder(OrderRegister data) {
        // Validar el ID del carrito
        Long cartId = data.cartId();
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with ID: " + cartId));

        // Validar el cliente
        Customer customer = cart.getCustomer();
        if (customer == null || !customer.getId().equals(data.customerId())) {
            throw new IllegalArgumentException("Invalid customer for the cart");
        }

        // Determinar el método de pago
        Payment paymentMethod = data.paymentMethod();

        // Calcular el total del carrito
        BigDecimal totalAmount = cartRepository.findTotalAmountById(cartId);
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid cart total amount");
        }

        // TODO: crear una manera para que se extraiga la cantidad de productos adquiridos por el cliente

        // Crear la orden
        Orders order = new Orders(
                null,
                customer,
                totalAmount,
                Status.PENDING,
                LocalDateTime.now(),
                cart,
                paymentMethod
        );

        // Guardar la orden
        ordersRepository.save(order);

        // Generar la factura
        generateInvoice(order);

        processPayment ( paymentMethod, totalAmount );

        // Retornar los datos de la orden
        return new OrderData(order);
    }

    // TODO: mejorar los métodos
    @Override
    public void processPayment( Payment paymentMethod, BigDecimal amount ) {
        PaymentContext paymentContext = new PaymentContext();
        switch ( paymentMethod ) {
            case CASH:
                paymentContext.setPaymentStrategy(new CashConcrete());
                break;
            case CREDIT_CARD:
                // Lógica para procesar pago con tarjeta de crédito
                break;
            case DEBIT_CARD:
                // Lógica para procesar pago con tarjeta de débito
                break;
            case PAYPAL:
                // Lógica para procesar pago con PayPal
                break;
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
        paymentContext.pay(amount);
    }



    @Override
    public void generateInvoice(Orders order) {
        // Aquí puedes implementar la lógica para generar una factura
        // Esto podría ser guardar la información en una tabla de facturas,
        // generar un PDF, enviar un correo electrónico, etc.

        // Ejemplo sencillo:
        System.out.println("Factura generada para la orden ID: " + order.getId());
        System.out.println("Total: " + order.getTotalAmount());
        System.out.println("Fecha: " + order.getDate());

    }

    @Override
    public OrderData getOrderById ( Long id ) {
        return ordersRepository.findById(id).map(OrderData::new).orElse( null );
    }

    //TODO: ordenes por cliente para solo obtener los pedidos de los clientes solo para el usuario que este registrado
    @Override
    @Transactional(readOnly = true)
    public List < OrderData > getAllOrders () {
        // debería poderse ver las órdenes por cliente
        return  ordersRepository.findAll().stream().map(OrderData::new).toList();
    }
}