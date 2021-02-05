package babySitter;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BabySitterFrame extends JFrame implements ActionListener { 
  
	private static final long serialVersionUID = -6244968032644162501L;
	
	private static final String START_TIME_AFTER_END_TIME = "Start time cannot be after End time.";
	private static final String TIMES_ARE_REQUIRED = "Please enter both Start time, and End Time.";
	
    private Container container; 
    private JLabel title; 
    private JLabel instructions;
    private JButton submit; 
    private JButton clear; 
    private JLabel userMessage; 
    private JLabel startTimesLabel;
    private JComboBox<BabySitterEventTime> startTimes;
    private JLabel bedTimesLabel;
    private JComboBox<BabySitterEventTime> bedTimes;
    private JLabel endTimesLabel;
    private JComboBox<BabySitterEventTime> endTimes;
    
    //constructor
    public BabySitterFrame() 
    { 
    	//set up the frame
        setTitle("Babysitter Pay Calculator"); 
        setBounds(300, 90, 900, 600); 
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
        setResizable(false); 
  
        //set up container
        createAndFillContainer();
        setVisible(true); 
    } 
  
    //two panel events are 'submit' and 'clear'
    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == submit) { 
        	
        	BabySitterEventTime startTime = (BabySitterEventTime) startTimes.getSelectedItem();
        	BabySitterEventTime endTime = (BabySitterEventTime) endTimes.getSelectedItem();
        	BabySitterEventTime bedTime = (BabySitterEventTime) bedTimes.getSelectedItem();
        	
        	if (startTime.getTimeId() == 0 || endTime.getTimeId() == 0) {
        		userMessage.setText(TIMES_ARE_REQUIRED);
        		
        	} else if (startTime.getTimeId() > endTime.getTimeId()) {
        		userMessage.setText(START_TIME_AFTER_END_TIME);
        		
        	} else {
        		userMessage.setText("Total wage earned: $" + computeWage(startTime, bedTime, endTime));
        	}
        	
        } else if (e.getSource() == clear) { 
            userMessage.setText("");
            startTimes.setSelectedIndex(0);
            bedTimes.setSelectedIndex(0);
            endTimes.setSelectedIndex(0);
        } 
    }
    
    protected Integer computeWage(BabySitterEventTime startTime, BabySitterEventTime bedTime, BabySitterEventTime endTime) {
    	int totalWage = 0;
    	if (startTime != null && bedTime != null && endTime != null) {
    		//if start time = end time, there are no wages
    		if (startTime.getTimeId() != endTime.getTimeId()) {
	    		int postMidNightWage = BabySitterCalculationUtils.computePostMidnightWage(startTime.getTimeId(), endTime.getTimeId());
	    		int bedTimeWage = BabySitterCalculationUtils.computeBedTimeWage(startTime.getTimeId(), bedTime.getTimeId(), endTime.getTimeId());
	    		int remainingWage = BabySitterCalculationUtils.computeRemainingWage(startTime.getTimeId(), bedTime.getTimeId(), endTime.getTimeId());
    		
	    		totalWage = postMidNightWage + bedTimeWage + remainingWage;
    		}
    	}
    	return totalWage;
    }
    
    private void createAndFillContainer() {
    	
        container = getContentPane(); 
        container.setLayout(null); 
        Map<BabySitterEventTime, Integer> possibleTimes = createAndPopulateMap();
        
        title = new JLabel("Nightly Wages"); 
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setSize(300, 60); 
        title.setLocation(300, 15); 
        container.add(title); 
  
        instructions = new JLabel("Select Start time, and End time (and Bed time if applicable)"); 
        instructions.setFont(new Font("Arial", Font.BOLD, 20)); 
        instructions.setSize(1000, 40); 
        instructions.setLocation(150, 60); 
        container.add(instructions); 
        
        startTimesLabel = new JLabel("Start time:");
        startTimesLabel.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 20)); 
        startTimesLabel.setSize(250, 25); 
        startTimesLabel.setLocation(120, 150); 
        container.add(startTimesLabel); 
                
        startTimes = createComboBox(possibleTimes);
        startTimes.setFont(new Font("Arial", Font.PLAIN, 15));
        startTimes.setSize(100, 25);
        startTimes.setLocation(245, 150); 
        container.add(startTimes);

        bedTimesLabel = new JLabel("Bed time:");
        bedTimesLabel.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 20)); 
        bedTimesLabel.setSize(250, 25); 
        bedTimesLabel.setLocation(120, 250); 
        container.add(bedTimesLabel); 
  
        bedTimes = createComboBox(possibleTimes);
        bedTimes.setFont(new Font("Arial", Font.PLAIN, 15));
        bedTimes.setSize(100, 25);
        bedTimes.setLocation(245, 250); 
        container.add(bedTimes);
        
        endTimesLabel = new JLabel("End time:");
        endTimesLabel.setFont(new Font("Verdana", Font.LAYOUT_LEFT_TO_RIGHT, 20)); 
        endTimesLabel.setSize(250, 25); 
        endTimesLabel.setLocation(120, 350); 
        container.add(endTimesLabel); 
  
        endTimes = createComboBox(possibleTimes);
        endTimes.setFont(new Font("Arial", Font.PLAIN, 15));
        endTimes.setSize(100, 25);
        endTimes.setLocation(245, 350); 
        container.add(endTimes);
  
        submit = new JButton("Submit"); 
        submit.setFont(new Font("Verdana", Font.PLAIN, 15)); 
        submit.setSize(100, 25); 
        submit.setLocation(300, 450); 
        submit.addActionListener(this); 
        container.add(submit); 
  
        clear = new JButton("Clear"); 
        clear.setFont(new Font("Verdana", Font.PLAIN, 15)); 
        clear.setSize(100, 25); 
        clear.setLocation(500, 450); 
        clear.addActionListener(this); 
        container.add(clear); 
  
        userMessage = new JLabel(""); 
        userMessage.setFont(new Font("Verdana", Font.PLAIN, 20)); 
        userMessage.setSize(500, 30); 
        userMessage.setLocation(120, 500); 
        container.add(userMessage); 
    }
    
    private JComboBox<BabySitterEventTime> createComboBox(final Map<BabySitterEventTime, Integer> map) {
        final JComboBox<BabySitterEventTime> startAndEndTimeSelector = new JComboBox<BabySitterEventTime>();
        
        for (BabySitterEventTime time : map.keySet()) {
        	startAndEndTimeSelector.addItem(time);
        }

        return startAndEndTimeSelector;
    }
    
    private Map<BabySitterEventTime, Integer> createAndPopulateMap() {
        
    	Map<BabySitterEventTime, Integer> map = new HashMap<>();
    	
     	BabySitterEventTime select 		= new BabySitterEventTime(0, "Select");
    	BabySitterEventTime fivePm 		= new BabySitterEventTime(5, "5:00 PM");
    	BabySitterEventTime sixPm		= new BabySitterEventTime(6, "6:00 PM");
    	BabySitterEventTime sevenPm 	= new BabySitterEventTime(7, "7:00 PM");
    	BabySitterEventTime eightPm 	= new BabySitterEventTime(8, "8:00 PM");
    	BabySitterEventTime ninePm 		= new BabySitterEventTime(9, "9:00 PM");
    	BabySitterEventTime tenPm 		= new BabySitterEventTime(10, "10:00 PM");
    	BabySitterEventTime elevenPm 	= new BabySitterEventTime(11, "11:00 PM");
    	BabySitterEventTime twelvePm 	= new BabySitterEventTime(12, "12:00 PM");
    	BabySitterEventTime oneAm	 	= new BabySitterEventTime(13, "1:00 AM");
    	BabySitterEventTime twoAm	 	= new BabySitterEventTime(14, "2:00 AM");
    	BabySitterEventTime threeAm 	= new BabySitterEventTime(15, "3:00 AM");
    	BabySitterEventTime fourAm 		= new BabySitterEventTime(16, "4:00 AM");
    	
    	map.put(select, select.getTimeId());
    	map.put(fivePm, fivePm.getTimeId());
    	map.put(sixPm, 	sixPm.getTimeId());
    	map.put(sevenPm, sevenPm.getTimeId());
    	map.put(eightPm, eightPm.getTimeId());
    	map.put(ninePm, ninePm.getTimeId());
    	map.put(tenPm, tenPm.getTimeId());
    	map.put(elevenPm, elevenPm.getTimeId());
    	map.put(twelvePm, twelvePm.getTimeId());
    	map.put(oneAm, oneAm.getTimeId());
    	map.put(twoAm, twoAm.getTimeId());
    	map.put(threeAm, threeAm.getTimeId());
    	map.put(fourAm, fourAm.getTimeId());

    	TreeMap<BabySitterEventTime, Integer> sorted = new TreeMap<>(map);
    	
        return sorted;
    }
    
} 