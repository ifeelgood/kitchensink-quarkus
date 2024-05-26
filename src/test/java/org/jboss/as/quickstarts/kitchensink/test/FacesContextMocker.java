package org.jboss.as.quickstarts.kitchensink.test;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public abstract class FacesContextMocker extends FacesContext {
    final List<FacesMessage> messages = new ArrayList<>();

    private FacesContextMocker() {
    }

    @Override
    public void addMessage(String clientId, FacesMessage message) {
        messages.add(message);
    }

    @Override
    public List<FacesMessage> getMessageList() {
        return messages;
    }

    public static FacesContext mockFacesContext() throws IOException {
        FacesContext context = Mockito.mock(FacesContext.class);

        doAnswer(i -> {
                    setCurrentInstance(null);
                    return null;
                })
                .when(context).release();

        final List<FacesMessage> messages = new ArrayList<>();
        when(context.getMessageList()).thenReturn(messages);
        doAnswer(i -> messages.add(i.getArgument(1)))
                .when(context).addMessage(isNull(), any(FacesMessage.class));

        setCurrentInstance(context);
        return context;
    }
}

