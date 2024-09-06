package net.Ram.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
public class JournalEntries {
	  @Id
	    private Long id;
	    @NonNull
	    private String title;
	    private String content;
	    private LocalDateTime date;
}
