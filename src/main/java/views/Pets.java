package views;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pets
{
    private List<Pet> pets = new ArrayList<>();
    private Map<Integer, Pet> idToPetMap = new HashMap<>();
    private Map<String, Pet> petNameToPetmap = new HashMap<>();

    private Pets()
    {
    }

    public Pets(List<Pet> pets)
    {
        this.pets = pets;

        this.buildPetMaps();
    }

    private void buildPetMaps()
    {
        if (CollectionUtils.isNotEmpty(pets))
        {
            for (Pet pet : pets)
            {
                idToPetMap.put(pet.getPetId(), pet);

                petNameToPetmap.put(pet.getName(), pet);
            }
        }
    }

    public List<Pet> getPets()
    {
        return pets;
    }

    public void setPets(List<Pet> pets)
    {
        this.pets = pets;
    }
}
