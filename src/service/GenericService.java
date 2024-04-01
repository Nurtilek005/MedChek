package service;

public interface GenericService <T> {
    String add(Long  hospitalId, T t);

    String removeById(Long id, Long iid);

    String updateById(Long id, T t);
}
