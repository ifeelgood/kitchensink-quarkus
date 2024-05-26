package org.jboss.as.quickstarts.kitchensink.test;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public abstract class FacesContextMocker extends FacesContext {
    List<FacesMessage> messages = new ArrayList<>();

    private FacesContextMocker() {
    }

    private static final Release RELEASE = new Release();

    private static class Release implements Answer<Void> {
        @Override
        public Void answer(InvocationOnMock invocation) throws Throwable {
            setCurrentInstance(null);
            return null;
        }
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
        doAnswer(RELEASE).when(context)
                .release();
        ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        doNothing().when(externalContext).redirect(anyString());
        when(context.getExternalContext()).thenReturn(externalContext);
        final List<FacesMessage> messages = new ArrayList<>();
        when(context.getMessageList()).thenReturn(messages);
        doAnswer(i -> messages.add(i.getArgument(1))).when(context).addMessage(isNull(), any(FacesMessage.class));
        setCurrentInstance(context);
        return context;
    }
}

