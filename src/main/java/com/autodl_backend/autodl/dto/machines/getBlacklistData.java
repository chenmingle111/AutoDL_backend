package com.autodl_backend.autodl.dto.machines;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * Data DTO for blacklist response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class getBlacklistData {
    private List<geteBlacklistItem> list;
}
