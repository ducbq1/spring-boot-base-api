package vn.vietinbank.evolve.user.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericRowMapper<T> implements RowMapper<T> {
    
    private Class<T> modelClass;
    
    // Constructor nhận lớp đối tượng cần ánh xạ
    public GenericRowMapper(Class<T> modelClass) {
        this.modelClass = modelClass;
    }
    
    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            // Tạo đối tượng từ lớp modelClass
            T object = modelClass.getDeclaredConstructor().newInstance();
            
            // Duyệt qua tất cả các trường trong lớp modelClass
            for (Field field : modelClass.getDeclaredFields()) {
                field.setAccessible(true); // Đảm bảo có thể truy cập trường private
                
                String columnName = field.getName(); // Tên cột trong DB phải trùng với tên trường trong class
                Object value = rs.getObject(columnName); // Lấy giá trị từ ResultSet theo tên cột
                
                field.set(object, value); // Gán giá trị vào trường của đối tượng
            }
            
            return object;
        } catch (Exception e) {
            throw new SQLException("Error mapping row to model", e);
        }
    }
}
