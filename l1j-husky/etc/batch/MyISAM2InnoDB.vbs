Set fso = CreateObject("Scripting.FileSystemObject")
Set src = fso.GetFolder("..\..\db\MyISAM")

For Each FullName In src.Files
	MyISAM2InnoDB FullName
Next

' �w��̃t�H���_�Ɋi�[���ꂽ�e�T�u�t�H���_����������
tmpMessage = "�t�H���_�ꗗ:" & vbNewLine
For Each FolderName In src.Subfolders
    tmpMessage = tmpMessage & FolderName & vbNewLine
Next
WScript.Echo "MyISAM����InnoDB�֕ϊ��I���܂����B"

Sub MyISAM2InnoDB(FullName)
	Set fso = CreateObject("Scripting.FileSystemObject")
	FileName = fso.GetFileName(FullName)
	Set destFolder = fso.GetFolder("..\..\db\InnoDB")
	Set destFile = destFolder.CreateTextFile(FileName)

	Set srcFile = fso.OpenTextFile(FullName) ' �e�L�X�g�t�@�C���̃I�[�v��
	Do Until srcFile.AtEndOfStream
		destFile.WriteLine( replace(srcFile.ReadLine,"MyISAM","InnoDB"))
	Loop
	destFile.close()
	srcFile.close()
End Sub
