package by.pvt.newmultiproject.core.repository;

import by.pvt.newmultiproject.core.FileWorker;
import by.pvt.newmultiproject.core.domain.Basket;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BasketRepository extends FileWorker {

    public static String FILE = "D:\\Pvt Enterprise\\FirstWebServlet\\by-pvt-newmultiproject\\by-pvt-newmultiproject-core\\src\\main\\resources\\dbBasket";

    public BasketRepository() {
    }

    public static List<Basket> buckets = new ArrayList<>();

    public List<Basket> getAllBuckets() {
        Object object = deserializeObject(FILE);
        List<Basket> bucketList = new ArrayList<>();
        if (object instanceof List<?>) {
            bucketList = (List<Basket>) object;
        }
        return bucketList;
    }

    public Basket add(Basket bucket) {
        buckets = getAllBuckets();
        buckets.add(bucket);
        serializeObject(buckets, FILE);
        return bucket;
    }

    public Basket updateBucket(Long id, Basket newBucket)  {
        Object object = deserializeObject(FILE);
        if (object instanceof List<?>) {
            buckets = (List<Basket>) object;
        }
        Basket bucket = getBucketById(id);
        bucket.setOrderId(newBucket.getOrderId());
        bucket.setProductId(newBucket.getProductId());
        bucket.setCount(newBucket.getCount());
        serializeObject(buckets, FILE);
        return bucket;
    }

    public Basket getBucketById(Long id) {
        buckets = getAllBuckets();
        return buckets.stream().filter(bucket -> bucket.getId().equals(id)).findFirst().orElse(null);
    }

    public Basket getBucketById(Long id, Long orderId) {
        buckets = getBucketsByOrderId(orderId);
        return buckets.stream().filter(bucket -> bucket.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Basket> getBucketsByOrderId(Long orderId) {
        buckets = getAllBuckets();
        List<Basket> bucketList = buckets.stream().filter(bucket -> bucket.getOrderId().equals(orderId)).collect(Collectors.toList());
        if(bucketList.isEmpty()) {
            return new ArrayList<>();
        }
        return bucketList;
    }

    public List<Long> getProductIdByBucketList(Long orderId) {
        buckets = getBucketsByOrderId(orderId);
        List<Long> productIdList = buckets.stream().map(Basket::getProductId).collect(Collectors.toList());
        if(productIdList.isEmpty()) {
            return new ArrayList<>();
        }
        return productIdList;
    }



    public void delete(Long id) {
        buckets = getAllBuckets();
        if (buckets.isEmpty()) {
            return;
        }
        Basket bucket = getBucketById(id);
        buckets.remove(bucket);
        for (Basket bucket1 : buckets) {
            if(bucket1.getId() > id) {
                bucket1.setId(bucket1.getId() - 1);
            }
        }
        serializeObject(buckets, FILE);
        System.out.println(buckets);
    }
}
