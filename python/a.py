#aaa
from __builtin__ import raw_input

print("aaa")
'''
   类型 标识符 = values

   标识符 = value 
'''
a = 1000000000000000000000000000000000000
b = type(a)
print(b)
#float--num
c = 1.23
d = type(c)
print(d)
#复数--num
a = complex(1,2)
b= type(a)
print(a)
print(b)
passwd = raw_input("请输入密码")
print("密码%s"%passwd)