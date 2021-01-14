# -*-coding:utf-8-*-
from functools import reduce
from States.BaseNodes import RootNode, SwitchNode, ActionNode
from SlotRecognition.SlotRecognition import SlotRecognitionUtil
import sys

class ActionClassFactory:
    @classmethod
    def parseJson(cls, nodeDict):
        NodeID = nodeDict['id']
        NodeName = nodeDict['name']
        NodeDescription = nodeDict['description']
        SceneName = nodeDict['scene_name']
        ActionType = nodeDict['other_info']['actionType']
        Reply = nodeDict['other_info']['reply']
        EntityName = nodeDict['other_info']['entityName'] if 'entityName' in nodeDict['other_info'] else ''
        Wires = nodeDict['wires']
        return (NodeID, NodeName, NodeDescription, SceneName, ActionType, Reply, EntityName, Wires)

    @classmethod
    def parseState(cls, jsonStr):
        NodeID, NodeName, NodeDescription, SceneName, ActionType, Reply, EntityName, Wires = cls.parseJson(jsonStr)

        def __init__(self):
            # super.__init__(self)
            self.NodeID = NodeID
            self.NodeName = NodeName
            self.NodeDescription = NodeDescription
            self.SceneName = SceneName
            self.ActionType = ActionType
            self.Reply = Reply
            self.EntityName = EntityName
            self.firstFlag = True
            self.Wires = Wires

        def WriteProgram(self, w):
            if self.ActionType == "REQUEST_INPUT":
                self.DealRequestInput(w)
            elif self.ActionType == "TO_MANUAL":
                self.DealtoManual(w)
            elif self.ActionType == "END_SESSION":
                self.DealEndSession(w)
            elif self.ActionType == "NONE":
                self.DealNone(w)
            else:
                pass

        def DealRequestInput(self, w):
            if self.firstFlag == True:
                self.firstFlag = False
                # print('flag changed to %s'%self.firstFlag)
                w.currentReply = self.Reply
                w.intentstate[w.context['intent']] = w.state
            else:
                self.firstFlag = True
                query = w.currentQuery.strip()
                if self.EntityName is not None and self.EntityName != '':
                    #pass
                    # recognize entities from user input
                    entities = SlotRecognitionUtil.recognize(query)
                    recognized_entity = [entity['entity_value'] for entity in entities if entity['entity_name'] == self.EntityName]
                    if len(recognized_entity) > 0:
                        w.context['entities'][EntityName] = recognized_entity[0]
                    # print('mySwitchNode in nodes? %s'%('mySwitchNode' in w.nodes))
                w.SetState(w.GetOrCreateNode(self.Wires[0]))
                w.state.WriteProgram(w)

        def DealtoManual(self, w):
            pass

        def DealEndSession(self, w):
            w.TaskFinished = True
            # TODO transfer @entities/&varibles to str
            w.currentReply = self.Reply

        def DealNone(self, w):
            pass

        cls_attrs = dict(__init__=__init__,
                         WriteProgram=WriteProgram,
                         DealRequestInput=DealRequestInput,
                         DealtoManual=DealtoManual,
                         DealEndSession=DealEndSession,
                         DealNone=DealNone)
        retClass = type(NodeID, (ActionNode,), cls_attrs)
        setattr(sys.modules[__name__], NodeID, retClass)
        return retClass


class RootClassFactory():
    @classmethod
    def parseJson(cls, nodeDict):
        NodeID = nodeDict['id']
        NodeName = nodeDict['name']
        NodeDescription = nodeDict['description']
        SceneName = nodeDict['scene_name']
        Intent = nodeDict['other_info']['intent']
        EntitiesForExtend = nodeDict['other_info']['entitiesForExtend']
        Wires = nodeDict['wires']
        return (NodeID, NodeName, NodeDescription, SceneName, Intent, EntitiesForExtend, Wires)

    @classmethod
    def parseState(cls, jsonStr):
        NodeID, NodeName, NodeDescription, SceneName, Intent, EntitiesForExtend, Wires = cls.parseJson(jsonStr)

        def __init__(self):
            # super.__init__(self)
            self.NodeID = NodeID
            self.NodeName = NodeName
            self.NodeDescription = NodeDescription
            self.SceneName = SceneName
            self.Intent = Intent
            self.EntitiesForExtend = EntitiesForExtend
            self.Wires = Wires

        def WriteProgram(self, w):
            # recognize entities from user input
            query = w.currentQuery.strip()
            for entity_name in self.EntitiesForExtend:
                entities = SlotRecognitionUtil.recognize(query)
                recognized_entity = [entity['entity_value'] for entity in entities if
                                     entity['entity_name'] == entity_name]
                if len(recognized_entity) > 0:
                    w.context['entities'][entity_name] = recognized_entity[0]
            w.SetState(w.GetOrCreateNode(self.Wires[0]))
            w.state.WriteProgram(w)

        cls_attrs = dict(__init__=__init__,
                         WriteProgram=WriteProgram)
        retClass = type(NodeID, (RootNode,), cls_attrs)
        setattr(sys.modules[__name__], NodeID, retClass)
        return retClass


class SwitchClassFactory():

    @classmethod
    def parseJson(cls, nodeDict):
        NodeID = nodeDict['id']
        NodeName = nodeDict['name']
        NodeDescription = nodeDict['description']
        SceneName = nodeDict['scene_name']
        Cases = nodeDict['other_info']['cases']
        Wires = nodeDict['wires']
        return (NodeID, NodeName, NodeDescription, SceneName, Cases, Wires)

    @classmethod
    def parseState(cls, jsonStr):
        NodeID, NodeName, NodeDescription, SceneName, Cases, Wires = cls.parseJson(jsonStr)

        def __init__(self):
            # super.__init__(self)
            self.NodeID = NodeID
            self.NodeName = NodeName
            self.NodeDescription = NodeDescription
            self.SceneName = SceneName
            self.Cases = Cases
            self.Wires = Wires

        def WriteProgram(self, w):
            idx = ruleidx(self.Cases, w)
            print('idx:{}, wire:{}'.format(idx, Wires[idx]))
            w.SetState(w.GetOrCreateNode(self.Wires[idx]))
            w.state.WriteProgram(w)

        def switch_dict(fun, *args):
            return {
                'equal': lambda: args[0] == args[1],
                'notEqual': lambda: args[0] != args[1],
                'greaterThan': lambda: args[0] > args[1],
                'lessThan': lambda: args[0] < args[1],
                'greaterThanOrEqual': lambda: args[0] >= args[1],
                'lessThanOrEqual': lambda: args[0] <= args[1],
                'isNull': lambda: args[0].strip() == '',
                'isNotNull': lambda: args[0].strip() != '',
                'contains': lambda: args[1] in args[0]
                # 'regex': lambda:
            }.get(fun, None)()

        def calonebool(rule, w):
            print(rule)
            value = rule['value'] if 'value' in rule else ''
            fun = rule['operator']
            if rule['targetType'] == 'query':
                key = w.currentQuery
            if rule['targetType'] == 'entity':
                #        key = w.context['entity'][rule['entityName']]
                key = None
                if rule['entityName'] in w.context['entities']:
                    key = w.context['entities'][rule['entityName']]
            print('fun: {}, key:{} value:{}'.format(fun, key, value))
            print(switch_dict(fun, key, value))
            return switch_dict(fun, key, value)

        def checkcase(case, w):
            print(case)
            if 'relation' not in case and case['rules'][0]['operator'] == 'else':
                return True
            if case['relation'].lower() == 'and':
                return reduce(lambda x, y: x and y, map(lambda x: calonebool(x, w), case['rules']))
            if case['relation'].lower() == 'or':
                return reduce(lambda x, y: x or y, map(lambda x: calonebool(x, w), case['rules']))
            else:
                return True

        def ruleidx(cases, w):
            for idx, case in enumerate(cases):
                if checkcase(case, w):
                    return idx

        cls_attrs = dict(__init__=__init__,
                         WriteProgram=WriteProgram,
                         switch_dict=switch_dict,
                         calonebool=calonebool,
                         checkcase=checkcase,
                         ruleidx=ruleidx)
        retClass = type(NodeID, (SwitchNode,), cls_attrs)
        setattr(sys.modules[__name__], NodeID, retClass)
        return retClass


class StateClassFactory():
    @classmethod
    def generateClasses(cls, json):
        mydict = {
            'switch': SwitchClassFactory,
            'root': RootClassFactory,
            'action': ActionClassFactory
        }
        classesDict = {}
        roots ={}
        for node in json['nodes']:
            classesDict[node['id']]= mydict[node['type']].parseState(node)
            if node['type'] == 'root':
                roots[node['other_info']['intent']] = node['id']
        '''
        classesDict = {node['id']: mydict[node['type']].parseState(node) for node in json['nodes']}
        for id, stateclass in classesDict.items():
            print('id %s: %s'%(id, issubclass(stateclass, ActionNode)))
        roots = [node['id'] for node in json['nodes'] if node['type'] == 'root'][0]
        '''
        rootclassesDict = {'roots': roots, 'classesDict': classesDict}
        return rootclassesDict
