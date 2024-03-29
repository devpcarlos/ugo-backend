package com.ugo.k;

import com.myzlab.k.KBuilder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class K extends KBuilder {
    
    public static String JDBC_MASTER = "JDBC_MASTER";

    @Autowired
    private JdbcTemplate master;

    @Override
    public Map<String, JdbcTemplate> getJdbcTemplates() {
        final Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>();
        
        jdbcTemplates.put(JDBC_MASTER, master);
        
        return jdbcTemplates;
    }

    @Override
    public String getJdbcTemplateDefaultName() {
        return JDBC_MASTER;
    }
}
