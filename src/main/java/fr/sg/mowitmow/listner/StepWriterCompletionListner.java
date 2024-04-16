package fr.sg.mowitmow.listner;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import fr.sg.mowitmow.bean.Position;

public class StepWriterCompletionListner implements ItemWriteListener<List<Position>> {
	
	@Override
	public void afterWrite(Chunk<? extends List<Position>> items) {
		//jobExecution.setExitStatus(ExitStatus.STOPPED);
	}

}
