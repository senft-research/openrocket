package info.openrocket.core.file.openrocket.importt;

import info.openrocket.core.file.simplesax.AbstractElementHandler;
import info.openrocket.core.file.simplesax.ElementHandler;
import info.openrocket.core.file.simplesax.PlainTextHandler;
import info.openrocket.core.logging.MessagePriority;
import info.openrocket.core.logging.WarningSet;
import info.openrocket.core.logging.warning.WarningType;
import info.openrocket.core.logging.warning.context.WarningContext;
import info.openrocket.core.logging.warning.factories.contexts.SimulationWarningContextFactory;
import info.openrocket.core.logging.warning.factories.warnings.SimulationWarningFactory;
import org.xml.sax.SAXException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WarningContextHandler extends AbstractElementHandler {

    private UUID warningId;
    private WarningType warningType;
    private MessagePriority warningPriority;
    private WarningSet warningSet;
    private final Map<String, String> contextElements = new HashMap<>();

    public WarningContextHandler(WarningSet warningSet) {
        this.warningSet = warningSet;
    }
    @Override
    public ElementHandler openElement(String element, HashMap<String, String> attributes, WarningSet warnings) throws SAXException {
        return PlainTextHandler.INSTANCE;
    }

    @Override
    public void closeElement(String element, HashMap<String, String> attributes, String content, WarningSet warnings) throws SAXException {
        switch (element) {
            case "id":
                warningId = UUID.fromString(content);
                break;
            case "type":
                warningType = WarningType.valueOf(content.toUpperCase());
                break;

            case "priority":
                warningPriority = MessagePriority.valueOf(content.toUpperCase());
                break;

            default:
                contextElements.put(element, content);
        }
    }

    @Override
    public void endHandler(String element, HashMap<String, String> attributes, String content, WarningSet warnings) throws SAXException {
        WarningContext context = SimulationWarningContextFactory.getInstance().create(warningPriority,warningId, warningType, contextElements);
        warningSet.add(SimulationWarningFactory.getInstance().createWarning(context));
    }


}
