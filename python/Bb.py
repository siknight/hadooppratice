class Test(object):
    #普通方法
    def test(self):
        print("普通方法test")
    #普通方法
    def _test1(self):
        print("普通方法_test1方法")
    #私有方法
    def __test2(self):
        print("私有方法__test2方法")
    @classmethod
    def parseJson(cls):
        print("cls=",cls)

# 创建一个对象，并用变量tom来保存它的引用
def main():
    # t = Test()
    # t.test()
    # t._test1()
    # t.__test2()
    Test.parseJson()


#防止测试代码被调用
if __name__=="__main__":
    main()