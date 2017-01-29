package ua.goit.offline4.dao;


import org.springframework.transaction.annotation.Transactional;
import ua.goit.offline4.entity.Component;

import java.math.BigDecimal;
import java.util.Collection;

public class ComponentsService {
    private ComponentDao componentDao;

    public ComponentsService(ComponentDao componentDao) {
        this.componentDao = componentDao;
    }

    @Transactional(readOnly = true)
    public Component get(long id) {
        return componentDao.get(id);
    }

    @Transactional(readOnly = true)
    public Collection<Component> getAll() {
        return componentDao.getAll();
    }

    @Transactional(readOnly = true)
    public Collection<Component> gtByPrize(BigDecimal prize) {
        return componentDao.gtByPrize(prize);
    }

    @Transactional
    public Component add(String name, BigDecimal prize) {
        return componentDao.add(name, prize);
    }

    @Transactional
    public boolean update(Component component) {
        return componentDao.update(component);
    }

    @Transactional
    public boolean delete(long id) {
        return componentDao.delete(id);
    }
}
