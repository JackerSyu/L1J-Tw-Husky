title L1J-TW �h��y�t
@echo off
goto Manu

::���
:Manu
echo L1J-TW �h��y�t Internationalization
echo �z�w�w�w�w�w�w�w�w�w�w�w�w�w�w�w�w�w�{
echo �x           �h��y�t�ഫ�@�@�@�@�@ �x
echo �|�w�w�w�w�w�w�w�w�w�w�w�w�w�w�w�w�w�}
echo ---------------------------------------
echo (1) �ഫ zh_TW
echo (2) �ഫ jp_JP
echo (3) �ഫ zh_TW + �ഫ jp_JP
echo (4) ���}
echo ---------------------------------------
echo �п��:
set /p num=����:
echo ��ܤF%num%
goto go%num%




::============================�H�U�O��ƳB�z================================

::�ഫ �c�餤��
::���Ъ`�N���|�����Эק令�ۤv��Jdk/bin���U��native2ascii.exe���|��
::zh_TW
:go1
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_zh_TW.txt ../data/language/messages_zh_TW.properties
goto Manu



::�ഫ ���
::���Ъ`�N���|�����Эק令�ۤv��java/bin���U��native2ascii.exe���|��
::jp_JP
:go2
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_ja_JP.txt ../data/language/messages_ja_JP.properties
goto Manu

::�ഫ �c�餤�� + ���
::���Ъ`�N���|�����Эק令�ۤv��java/bin���U��native2ascii.exe���|��
::zh_TW + jp_JP
:go3
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_zh_TW.txt ../data/language/messages_zh_TW.properties
"C:\Program Files\Java\jdk1.6.0_23\bin\native2ascii.exe" -encoding UTF-8 ../data/language/messages_ja_JP.txt ../data/language/messages_ja_JP.properties
goto Manu

::���}
:go4
exit


