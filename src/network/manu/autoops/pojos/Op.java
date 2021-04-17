package network.manu.autoops.pojos;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.UUID;

public class Op {
    @Getter @Setter private ObjectId id;
    @Getter @Setter private UUID uuid;

    public Op(UUID uuid) {
        this.uuid = uuid;
    }

    public Op() {
    }
}
