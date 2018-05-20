package com.somia.fyp.assistant;

import android.content.Context;
import android.util.Log;

import com.somia.fyp.assistant.commands.CallCommand;
import com.somia.fyp.assistant.commands.InstructionsCommand;
import com.somia.fyp.assistant.commands.TensorFlowCameraCommand;
import com.somia.fyp.assistant.commands.ShareLocationCommand;
import com.somia.fyp.assistant.commands.DecreaseVolumeCommand;
import com.somia.fyp.assistant.commands.IncreaseVolumeCommand;
import com.somia.fyp.assistant.commands.MapCommand;
import com.somia.fyp.assistant.commands.NextSongCommand;
import com.somia.fyp.assistant.commands.PauseMuusicCommand;
import com.somia.fyp.assistant.commands.PlayMusicCommand;
import com.somia.fyp.assistant.commands.PreviousSongCommand;
import com.somia.fyp.assistant.commands.ReadSmsCommand;
import com.somia.fyp.assistant.commands.SendSmsCommand;
import com.somia.fyp.assistant.commands.WifiOffCommand;
import com.somia.fyp.assistant.commands.WifiOnCommand;
import com.somia.fyp.utial.MyTextToSpeech;

import java.util.ArrayList;


public class CommandInvoker {
    private static Command [] commands;

    public static Command[] getCommands() {
        if(commands==null)
        {
            commands = new Command[] {new CallCommand(),new ReadSmsCommand(),new PauseMuusicCommand(),
                    new PreviousSongCommand(),new NextSongCommand(),new PlayMusicCommand(),
                    new IncreaseVolumeCommand(),new DecreaseVolumeCommand(), new SendSmsCommand()
                    ,new WifiOnCommand(),new WifiOffCommand(), new MapCommand(),new ShareLocationCommand(),
                    new TensorFlowCameraCommand(),new InstructionsCommand(),new MapCommand()};
        }
        return commands;
    }
    public static boolean excute(final Context context, ArrayList<String> phrases)
    {

        boolean isCommandFound=false;
        for (final Command command : getCommands())
        {
            String[] activationPhrases = command.getDefaultPhrase().split(",");
           for(String phrase : phrases){
               for(String activatingParts : activationPhrases)
               {
                   if(isCommandFound)
                       break;
                   if(phrase.equalsIgnoreCase(activatingParts))
                   {
                       isCommandFound=true;

                       command.execute(new CommandModel(phrase.replace(activatingParts.toLowerCase(),""),phrase,context));
                       Log.d("commander","comand found");
                       try {
                           MyTextToSpeech.intiTextToSpeech(context,"hi",command.getTtsPhrase(context));
                       }catch (Exception e)
                       {
                           e.printStackTrace();
                       }


                   }
               }
           }
        }
        return isCommandFound;
    }
}
