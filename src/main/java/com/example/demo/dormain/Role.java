package com.example.demo.dormain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import static javax.persistence.GenerationType.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
}
