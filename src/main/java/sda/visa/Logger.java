package sda.visa;
public class Logger {
    public static int CitizenshipIsnull = 0;
    public static int DoubleCitizenshipIDIsnull = 0;
    public static int ChangedPresidentDecreeDate = 0;
    public static int ChangedCitizenship = 0;
    public static int ChangedDoubleCitizenship = 0;
    public static int ChangedRealEndDate = 0;
    public static int IdIsnull = 0;
    public static int NameIsnull = 0;
    public static int DateOfBirthIsnull = 0;
    public static int OperationTypeIsnull = 0;
    public static int TaskStatusIsnull = 0;
    public static int EndDateIsnull = 0;
    public static int DateCount = 0;
    public static int BadFormatDateCount = 0;
    public static int NotValidatedDateCount = 0;
    public static String NotValidatedDateFormat = "";
    public static int LongsCount = 0;
    public static int BadFormatLongCount = 0;
    public static int NamesCount = 0;
    public static int ChangedNameCount = 0;
    public static int DeletedNameCount = 0;
    public static int TextCount = 0;
    public static int ChangedTextCount = 0;
    public static int DeletedTextCount = 0;
    public static int LibraryCount = 0;
    public static int ChangedLibraryCount = 0;
    public static int DeletedLibraryCount = 0;


    public static String GetLog(){
        String result = "";
        result =//"CitizenshipIsnull = " + CitizenshipIsnull + ";\n" +
//                "DoubleCitizenshipIDIsnull = " + DoubleCitizenshipIDIsnull + ";\n" +
//                "LongsCount = " + LongsCount + ";\n" +
//                "BadFormatLongCount = " + BadFormatLongCount + ";\n" +
//                "ChangedPresidentDecreeDate = " + ChangedPresidentDecreeDate + ";\n" +
//                "ChangedCitizenship = " + ChangedCitizenship + ";\n" +
//                "ChangedDoubleCitizenship = " + ChangedDoubleCitizenship + ";\n" +
//                "ChangedRealEndDate = " + ChangedRealEndDate + ";\n" +
//                "IdIsnull = " + IdIsnull + ";\n" +
//                "DateOfBirthIsnull = " + DateOfBirthIsnull + ";\n" +
//                "NameIsnull = " + NameIsnull + ";\n" +
//                "OperationTypeIsnull = " + OperationTypeIsnull + ";\n" +
//                "TaskStatusIsnull = " + TaskStatusIsnull + ";\n" +
//                "EndDateIsnull = " + EndDateIsnull + ";\n" +
//                "NamesCount = " + NamesCount + ";\n" +
//                "ChangedNameCount = " + ChangedNameCount + ";\n" +
//                "DeletedNameCount = " + DeletedNameCount + ";\n" +
//                "TextCount = " + TextCount + ";\n" +
//                "ChangedTextCount = " + ChangedTextCount + ";\n" +
//                "DeletedTextCount = " + DeletedTextCount + ";\n" +
//                "LibraryCount = " + LibraryCount + ";\n" +
//                "ChangedLibraryCount = " + ChangedLibraryCount + ";\n" +
                "DeletedLibraryCount = " + DeletedLibraryCount;
        return  result;
    }
}
