package fr.sg.mowitmow.step;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import fr.sg.mowitmow.bean.Position;

@Component
public class Writer implements ItemWriter<List<Position>> {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void write(Chunk<? extends List<Position>> positions) throws Exception {
		logger.info("****************** Results **********************");
		for (List<Position> position : positions) {
			logger.info("Mower final position : " + position);
		}
		logger.info("*************************************************");
		
	}

}
