package fr.sg.mowitmow.step;

import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import fr.sg.mowitmow.MowerControl;
import fr.sg.mowitmow.bean.Position;

@Component
public class Processor implements ItemProcessor<List<String>, List<Position>> {

	@Override
	public List<Position> process(List<String> lines) throws Exception {
		return new MowerControl(lines).process();
	}
}
