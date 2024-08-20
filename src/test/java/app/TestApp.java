package app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import prac.shpp.App;

import java.io.IOException;

class TestApp {




    @Test
    void testNumberTypeStringIsNull() throws IOException {
        App mockApp = Mockito.mock(App.class);

        System.setProperty("myProperty", "nonsense");

        mockApp.main(null);

        Mockito.verify(mockApp).main(Mockito.isNull());

    }

}
