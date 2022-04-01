package io.rbs;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;

@Controller
public class SomeController {

    @Property(name = "myConf")
    private String myConf;

    @Get
    @Produces(MediaType.TEXT_PLAIN)
    String getMyValue() {
        return myConf;
    }
}
