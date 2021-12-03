package com.example.cdnaskydrivejava.model;

import lombok.Data;

@Data
public class ReturnMode<T> {
    T Data;
    String Message;
}
