package com.scand.test.service.implementation;

import com.scand.test.model.CoffeeType;
import com.scand.test.model.wrapper.CoffeeWrapper;
import com.scand.test.repository.CoffeeTypeRepository;
import com.scand.test.service.CoffeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class CoffeeTypeServiceImpl implements CoffeeTypeService
{

    private final CoffeeTypeRepository coffeeTypeRepository;

    @Autowired
    public CoffeeTypeServiceImpl(CoffeeTypeRepository coffeeTypeRepository)
    {
        this.coffeeTypeRepository = coffeeTypeRepository;
    }

    @Override
    public CoffeeType findById(Long id)
    {
        return coffeeTypeRepository.findById(id).get();
    }

    @Override
    public List<CoffeeType> findAllByOrderByPriceAsc()
    {
        return coffeeTypeRepository.findAllByOrderByPriceAsc();
    }

    /**
     * Метод проверяет наличие количества у выбранных кофе в заказе.
     * @param wrapper Обьект обёртка класса CoffeeType.
     * @return true - если у всех выбранных кофе есть количество.
     * @return false - если хотя бы у одного кофе отсутсвует количество.
     */
    @Override
    public boolean checkCoffeeAndCounts(CoffeeWrapper wrapper)
    {
        boolean answer = true;
        LinkedHashMap<CoffeeType,Integer> coffeeTypesAndCount = createCoffeeAndCountsLinkedHashMap(wrapper);
        for (CoffeeType coffeeType : wrapper.getSelectedCoffeeTypes())
        {
            Integer compared = coffeeTypesAndCount.get(coffeeType);
            if(compared == null || compared <= 0) {
                answer = false;
                break;
            }
        }
        return answer;
    }

    /**
     * Метод формирует коллекцию с типами кофе и выбранным количеством.
     * @param wrapper Обьект обёртка класса CoffeeType.
     * @return coffeeTypeAndCount - коллекция с типами кофе и их количеством.
     */
    @Override
    public LinkedHashMap<CoffeeType, Integer> createCoffeeAndCountsLinkedHashMap(CoffeeWrapper wrapper)
    {
        LinkedHashMap<CoffeeType, Integer> coffeeTypesAndCount = new LinkedHashMap<>();
        Iterator<CoffeeType> coffeeTypeIterator = findAllByOrderByPriceAsc().iterator();
        Iterator<Integer> coffeeCountsIterator = wrapper.getSelectedCoffeeCounts().iterator();
        while (coffeeTypeIterator.hasNext() && coffeeCountsIterator.hasNext()) {
            coffeeTypesAndCount.put(coffeeTypeIterator.next(),coffeeCountsIterator.next());
        }
        return coffeeTypesAndCount;
    }
}
