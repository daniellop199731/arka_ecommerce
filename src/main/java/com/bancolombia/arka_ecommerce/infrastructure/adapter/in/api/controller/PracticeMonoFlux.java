package com.bancolombia.arka_ecommerce.infrastructure.adapter.in.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/practiceMonoFlux")
public class PracticeMonoFlux {

    /*
    Crea un Flux que emita los números del 1 al 10. Luego, utiliza los operadores filter y map para filtrar solo los números pares 
    y multiplicarlos por 2. Imprime el resultado en consola.
     */
    @GetMapping("/range/{numOne}/{numTwo}")
    public Flux<Integer> range(@PathVariable("numOne") int numOne, @PathVariable("numTwo") int numTwo) {
        return Flux.range(numOne, numTwo)   //Numeros entre el rango definido
            .filter(num -> num % 2 == 0)    //Del rango definido solo quedan los numeros pares
                                            //Con filter se pueden aplicar condiciones a los elementos emitidos, solo quedan los
                                            //cumplan con la condicion establecida, en este caso numeros que al ser dividos por dos
                                            //su reciduo sea cero.
            .map(num -> num * 2)            //Los numeros filtrados se multiplican por dos
                                            //Map se encarga de modificar los elementos emitidos.
            .doOnNext(num -> System.out.println(num)); //Por cada iteracion realiza una accion, en este caso mensaje por consola
    }

    /*
    Crea un Flux que emita los números del 1 al 10. Utiliza el operador reduce para calcular la suma total de estos números 
    y suscríbete para imprimir el resultado. 
     */
    @GetMapping("/totalized/{numOne}/{numTwo}")
    public Mono<Integer> totalized(@PathVariable("numOne") int numOne, @PathVariable("numTwo") int numTwo) {
        return Flux.range(numOne, numTwo)
                .reduce(0, Integer::sum)    //redece se encarga de volver a un solo dato un conjunto de datos
                                                    //Para este caso se indica que todos los valores se sumen
                .doOnNext(number -> System.out.println(number));
    }

    /* 
    Crea un Mono que simule una operación que puede fallar (por ejemplo, división por cero). Utiliza el operador 
    onErrorResume para manejar el error y devolver un valor predeterminado (por ejemplo, -1).
    */
    @GetMapping("/manageErr/{numOne}/{numTwo}")
    public Mono<Integer> manageErr(@PathVariable("numOne") int numOne, @PathVariable("numTwo") int numTwo) {
        return Mono.just(numOne)            //Se asigna el valor al mono, numOne
                .map(num -> num / numTwo)   //El valor de mono se modifica dividiendolo por numTwo
                .onErrorResume(ArithmeticException.class, error ->{        //En caso de producirce un ArithmeticException
                    System.out.println("Error: " + error.getMessage());         //Se muestra el mensaje de error por consola
                    return Mono.just(-1);                                       //Se retorna un Mono con valor en -1
                }); 

    }

    @GetMapping("/practice/{numOne}/{numTwo}")
    public Flux<Integer> practice(@PathVariable("numOne") int numOne, @PathVariable("numTwo") int numTwo) {

    Flux<Integer> numbers = Flux.range(1, 10)
        .map(i -> {
            /*if (i % 2 == 0) {
                throw new RuntimeException("Número par no permitido");
            }*/
            return i;
        })  //Recorre los numeros del 1 al 10, pero si encuentra un numero par se lanza una excepcion
        
        .onErrorResume(RuntimeException.class, error -> {
            System.out.println("Error: " + error.getMessage());
            return Mono.just(0);
        })  // Se captura la excepcion generada, y si cumple con el tipo, muestra un mensaje por consola
            //y retorna un Mono con un valor en cero

        //Gracias a la excepcion generada, el sistema solo llega hasta este punto y no continua.

        .filter(number -> number > 5)
        //Solo se tendran los numeros mayores a 5
        .flatMap(number -> {
            // Simular una operación asíncrona (por ejemplo, una llamada a una base de datos)
            return Mono.just(number * 2)
                    .delayElement(Duration.ofMillis(100));
        })        
        //Por cada numero hasta este punto crea un Mono que tendra como valor el numero multiplicado por dos
        //y hace un dalay o retraso de 100 milecimas por cada iteracion

        /*.subscribe(
            number -> System.out.println("Número procesado: " + number),
            error -> log.error("Error general: {}", error),
            () -> System.out.println("Completado")
        )*/;
        //Por cada iteracion muestra el numero proceso en consola
        //Si hay un error muestra un log de error
        //Al terminar todas las operaciones muestra un texto de completado

        return numbers;
    }    
    
    
    

}
