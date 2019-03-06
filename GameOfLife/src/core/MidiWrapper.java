package core;

// Wraps Georgia Tech's MidiPlayer class.  Instantiates lazily, and pipes the
// playNote method on through
public class MidiWrapper
{
    private static MidiPlayer midiPlayer = null;

    /**
     * Method to play a note
     * @param note the note to play (0 to 127, 60 is middle C)
     * @param duration how long to play the note in milliseconds
     */
    public static void playNote(int note, int duration)
    {
        ensureInitialized();
        System.out.println(note + "_" + duration);
        midiPlayer.playNote(note, duration);
    }
    
    /**
     * Method to set the instrument to play
     * @param num a number from 0 to 127 that represents
     * the instruments
     */
    public static void setInstrument(int num)
    {
        ensureInitialized();
        midiPlayer.setInstrument(num);
    }

    
    private static void ensureInitialized()
    {
        if (midiPlayer == null)
        {
            midiPlayer = new MidiPlayer();

            // The MIDI Player expects to be initialized shortly before it starts playing.
            // Our simplification for the students, though, lazily initializes on first
            // use.  As a result, the first played note lasts longer than it should, likely
            // due to initializing still occurring on another thread before the note off
            // event can be sent.  An extra sleep here before playing the first note seems to fix it.
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {

            }
        }
    }
}
