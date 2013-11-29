package Create;

import com.sleepycat.db.DatabaseEntry;
import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;
import com.sleepycat.persist.model.Relationship;
import com.sleepycat.persist.model.SecondaryKey;

@Entity
public class entityData {
	public String indexKey;
	public String indexData;
    @PrimaryKey
    	public String key;
    @SecondaryKey(relate= Relationship.ONE_TO_ONE)
    public String seckey;
    	public entityData() {
    		this.key = indexKey;
    		this.seckey = indexData;
    	}
    }

