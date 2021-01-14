class Cat(object):
    # 吃
    def eat(self):
        print('猫在吃鱼....')
        self.age =18
    # 喝东西
    def drink(self):
        print("猫在喝东西...")

# 创建一个对象，并用变量tom来保存它的引用
if __name__ == '__main__':
    tom = Cat()
    tom.eat()
    print(tom.age)
    tom.name="erhuo"
    print(tom.name)
