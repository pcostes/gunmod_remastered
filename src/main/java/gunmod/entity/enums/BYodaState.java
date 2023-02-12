package gunmod.entity.enums;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

public enum BYodaState {
	NORMAL("normal", 0),
	CLOSED("closed", 1),
	HAPPY("happy", 2),
	ATTACK("attack", 3);
	
	private static final Map<Integer, BYodaState> STATES = Maps.<Integer, BYodaState>newHashMap();
	private static final Map<String, BYodaState> BY_NAME = Maps.<String, BYodaState>newHashMap();
	
	private final int id;
	private final String name;
	
	private BYodaState(String name, int id)
	{
		this.id = id;
		this.name = name;
	}
	
	@Nullable
    public static BYodaState getById(int id)
    {
        return STATES.get(Integer.valueOf(id));
    }

    @Nullable
    public static BYodaState getByName(String name)
    {
        return BY_NAME.get(name);
    }
	
	public int getId()
    {
        return this.id;
    }
	
	public String getName()
	{
		return this.name;
	}
	
	static
    {
        for (BYodaState states : values())
        {
            STATES.put(Integer.valueOf(states.getId()), states);
            BY_NAME.put(states.getName(), states);
        }
    }
}
