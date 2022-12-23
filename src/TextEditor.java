import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;

    JMenuItem newfile,openfile,savefile;
    JMenuItem cut,copy,paste,selectall,close;

    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();
        menuBar = new JMenuBar();

        textArea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newfile = new JMenuItem("New");
        openfile = new JMenuItem("open");
        savefile = new JMenuItem("save");

        //adding action listener
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select all");
        close = new JMenuItem("Close Window");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);
        frame.add(textArea);

        frame.setBounds(200,200,800,500);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            textArea.copy();
        }
        if (actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectall){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==newfile){
            TextEditor newwindow = new TextEditor();
        }
        if(actionEvent.getSource()==openfile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseopt = fileChooser.showOpenDialog(null);
            if(chooseopt==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filepath = file.getPath();
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
                    String intermediate = "", output = "";

                    while((intermediate = bufferedReader.readLine())!=null){
                        output = output + intermediate+"\n";
                    }
                    textArea.setText(output);
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==savefile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseopt = fileChooser.showSaveDialog(null);
            if(chooseopt==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}