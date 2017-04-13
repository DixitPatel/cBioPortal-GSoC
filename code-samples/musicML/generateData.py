
# coding: utf-8

# In[7]:

from music21 import *
import os
import sys


# In[8]:

def _parse(file):
    midi = converter.parse(file)
    if midi is None:
        raise sys.exit('File path invalid')
    #depending where your melody is return appropriate index; using zero
    return midi[0]

    


# In[41]:

def generateNotes(fileName):
    #parse
    noteList = []
    part = _parse(fileName)
    print(part.getElementsByClass(tempo.MetronomeMark)[0].number)
    print('{0}/{1}'.format(part.getElementsByClass(meter.TimeSignature)[0].numerator,part.getElementsByClass(meter.TimeSignature)[0].denominator))
    for i,v in enumerate(part.getElementsByClass(stream.Voice)):
        notes = v.getElementsByClass(note.Note).notes;
        for i in notes:
            noteList.append(i)
    if noteList:
        print("Note/Rest,Octave,Length,Offset")
        for n in noteList:
            print('{0},{1},{2},{3}'.format(n.name,n.octave,n.quarterLength,float(n.offset)))
            
    else: raise sys.exit("Note List Empty")       
    


def generateChords(fileName):
    #parse
    chordList = []
    part = _parse(fileName)
    print(part.getElementsByClass(tempo.MetronomeMark)[0].number)
    print('{0}/{1}'.format(part.getElementsByClass(meter.TimeSignature)[0].numerator,part.getElementsByClass(meter.TimeSignature)[0].denominator))
    for idx,v in enumerate(part.getElementsByClass(stream.Voice)):
        chords = v.getElementsByClass(chord.Chord)
        for c in chords:
            chordList.append(c)
            
    if chordList:
        print("FullName CommonName Length Offset")
        for cl in chordList:
            print('{0},{1},{2},{3}'.format(cl.fullName,cl.pitchedCommonName,cl.quarterLength,float(cl.offset)))
    else: raise sys.exit("chord list empty")


# In[42]:

# TODO Improve toggling
#if('notes' == sys.argv[1]):
#print('Generating notes for MIDI')
generateNotes('/Users/Dixit/Documents/github/jazzml/Oscar-Peterson-2.mid')
#generateChords('/Users/Dixit/Documents/github/jazzml/Oscar-Peterson-2.mid')
#elif('chords' == sys.argv[1]):
#print('Generating Chords for MIDI')

#else :
    #raise sys.exit('Wrong Arg');


# In[ ]:



