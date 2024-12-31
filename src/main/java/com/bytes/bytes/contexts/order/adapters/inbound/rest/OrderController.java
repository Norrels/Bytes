package com.bytes.bytes.contexts.order.adapters.inbound.rest;

import com.bytes.bytes.contexts.order.adapters.inbound.dtos.UpdateOrderReq;
import com.bytes.bytes.contexts.order.application.OrderService;
import com.bytes.bytes.contexts.order.domain.models.Order;
import com.bytes.bytes.contexts.order.domain.models.dtos.CreateOrderDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Order", description = "Operações realizadas pelo estabelicimento")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Criar pedido")
    @PostMapping()
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderDTO order){
        try {
            Order newOrder = orderService.createOrder(order);
            return ResponseEntity.ok().body(newOrder);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Atualiza pedido")
    @PutMapping("/status")
    public ResponseEntity<Object> updateStatus(@RequestBody UpdateOrderReq order){
        try {
            orderService.updateStatus(order.id(), order.status(), order.modifyById());
            return ResponseEntity.ok().body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(summary = "Cancelar pedido")
    @PutMapping("/{id}")
    public ResponseEntity<Object> cancel(@PathVariable Long id, @RequestBody Long modifyById){
        try {
            orderService.cancelOrder(id, modifyById);
            return ResponseEntity.ok().body("");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(summary = "Buscar pedido por id")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){
        try {
            Order order = orderService.getOrderById(id);
            return ResponseEntity.ok().body(order);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
