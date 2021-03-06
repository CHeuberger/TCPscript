package cfh.tcpscript.command;

import java.text.ParseException;

import javax.naming.NameNotFoundException;

import cfh.tcpscript.Channel;
import cfh.tcpscript.ScriptEngine;

/**
 * TODO
 * 
 * @author Carlos Heuberger
 * $Revision: 1.7 $
 */
class SendCommand extends Command {

    SendCommand() {
        super("send", "<name>[-<sub>] <message>", "send message bytes over the channel");
    }
    
    @Override
    public void run(ScriptEngine executor, String arg) throws Exception {
        String[] words = arg.split("\\s++", 2);
        if (words.length < 2) {
            throw new ParseException(createUsageMesssage("missing arguments"), 0);
        }
        String name = words[0];
        Channel channel = Channel.get(name);
        if (channel == null)
            throw new NameNotFoundException(name);

        channel.sendData(0, words[1]);
    }
}
