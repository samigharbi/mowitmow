package fr.sg.mowitmow.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.sg.mowitmow.bean.Position;
import fr.sg.mowitmow.step.Processor;
import fr.sg.mowitmow.step.Reader;
import fr.sg.mowitmow.step.Writer;

@Component
public class MowerMoverTasklet implements Tasklet, InitializingBean{

	
	@Autowired
	private Reader reader;
	@Autowired
	private Writer writer;
	@Autowired
	private Processor processor;
	
	@Override
	public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
		List<String> lines  = reader.read();
		List<Position> positions = processor.process(lines);
		writer.write(new Chunk<List<Position>>(positions));
		return RepeatStatus.FINISHED;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
}
