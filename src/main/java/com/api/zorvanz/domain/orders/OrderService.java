package com.api.zorvanz.domain.orders;

import com.api.zorvanz.domain.order_details.OrderDetailData;
import com.api.zorvanz.domain.order_details.OrderDetails;
import com.api.zorvanz.domain.order_details.OrderDetailsRepository;
import com.api.zorvanz.domain.products.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    
    public OrderData createOrder(List<OrderDetailData> orderDetailsData) {
        Orders order = new Orders();
        double totalAmount = 0.0;
        
        for (OrderDetailData detailData : orderDetailsData) {
            // Recuperar el producto
            var product = productRepository.findById(detailData.productId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + detailData.productId()));
            
            // Verificar stock
            if (product.getStock() < detailData.quantity()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + product.getName());
            }
            
            // Crear y guardar el detalle de la orden
            var orderDetail = new OrderDetails();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailData.quantity());
            orderDetail.setUnitPrice(product.getPrice());
            orderDetail.setTotalAmount(); // Calcula totalAmount como unitPrice * quantity
            
            orderDetailsRepository.save(orderDetail);
            
            // Actualizar el total de la orden
           // totalAmount += orderDetail.getTotalAmount();
            
            // Reducir el stock del producto
            product.setStock(product.getStock() - detailData.quantity());
            productRepository.save(product);
        }
        
        // Asignar el total calculado a la orden y guardar la orden
        order.setTotalAmount(totalAmount);
        order.getCustomer().getId();
        order.setStatus(Status.PENDING); // Por ejemplo, status inicial puede ser PENDING
        order.setDate( LocalDateTime.now() );
        ordersRepository.save(order);
        
        // Generar la factura (opcionalmente, esto puede ser un método separado)
        generateInvoice(order);
        
        return new OrderData(order);
    }
    
    private void generateInvoice(Orders order) {
        // Aquí puedes implementar la lógica para generar una factura
        // Esto podría ser guardar la información en una tabla de facturas,
        // generar un PDF, enviar un correo electrónico, etc.
        
        // Ejemplo sencillo:
        System.out.println("Factura generada para la orden ID: " + order.getId());
        System.out.println("Total: " + order.getTotalAmount());
    }
    
    //TODO: ordenes por cliente para solo obtener los pedidos de los clientes solo para el usuario que este registrado
    public List<OrderData> getAllOrders () {
        // deberia poderse ver las ordenes por cliente
        return ordersRepository.findAll().stream().map(OrderData::new).toList();
    }
}
