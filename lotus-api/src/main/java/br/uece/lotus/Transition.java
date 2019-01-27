/*
 * The MIT License
 *
 * Copyright 2014 Universidade Estadual do Ceará.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.uece.lotus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Transition {

    public static class Builder {
        private final Component mComponent;
        private final Transition mTransition;

        Builder(Component c, Transition t) {
            mComponent = c;
            mTransition = t;
        }

        public Builder setValue(String s, Object o) {
            mTransition.setValue(s, o);
            return this;
        }

        public Builder setValues(Map<String,Object>values) {
            mTransition.setValues(values);
            return this;
        }

        public Transition create() {
            mComponent.add(mTransition);
            return mTransition;
        }

        public Builder setLabel(String label) {
            mTransition.setLabel(label);
            return this;
        }

        public Builder setParameters (List<String> parameters){
            mTransition.setParameters(parameters);
            return this;
        }

        public Builder setProbability(Double probability) {
            mTransition.setProbability(probability);
            return this;
        }

        public Builder setGuard(String guard) {
            mTransition.setGuard(guard);
            return this;
        }

        public Builder addAction(String action){
            mTransition.addAction(action);
            return this;
        }

        public Builder setActions(List<String> actions){
            mTransition.setActions(actions);
            return this;
        }

        public Builder setViewType(int type) {
            mTransition.setValue("view.type", type);
            return this;
        }

        public Builder addParameter(String parameter) {
            mTransition.addParameter(parameter);
            return this;
        }

        public Map<String, Object> getValues() {
            return mTransition.getValues();
        }
    }

    public void setValues(Map<String, Object> values) {
        this.mValues.clear();
        this.mValues.putAll(values);
    }

    public Map<String, Object> getValues() {
        return mValues;
    }

    public interface Listener {

        void onChange(Transition transition);
    }

    public static final String TEXTSTYLE_NORMAL = "normal";
    public static final String TEXTSTYLE_ITALIC = "italic";
    public static final String TEXTSTYLE_BOLD = "bold";
    //Graph properties
    private State mSource;
    private State mDestiny;
    private final Map<String, Object> mValues = new HashMap<>();
    private final List<Listener> mListeners = new ArrayList<>();
    //View properties
    private String mColor = "black";
    private int mWidth = 1;
    private String mTextColor = "black";
    private String mTextStyle = TEXTSTYLE_NORMAL;
    private int mTextSize = 13;
    //LTS properties
    private Double mProbability;
    private String mLabel;
    private String mGuard;
    private List<String> mActions = new ArrayList<>();
    private List<String> mParameters = new ArrayList<>();

    //Used in simulations
    private int mVisitedTransitionsCount;
    //Used in BigState
    private int propertyBigState = 0;

    public Transition(State source, State destiny) {
        mSource = source;
        mDestiny = destiny;
    }

    public List<String> getParameters() {
        return mParameters;
    }

    public void setParameters(List<String> parameters) {

        this.mParameters.clear();

        if(mParameters!= null){
            mParameters.addAll(new ArrayList<>(parameters));

            for (Listener l : mListeners) {
                l.onChange(this);
            }
        }


    }

    public void addParameter(String parameters){
        mParameters.add(parameters);

        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public void addAction(String action){
        this.mActions.add(action);
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public List<String> getActions() {
        return mActions;
    }

    public void setActions(List<String> actions) {

       mActions.clear();

       if(actions != null){
           this.mActions = new ArrayList<>(actions);
           for (Listener l : mListeners) {
               l.onChange(this);
           }
       }


    }

    public State getSource() {
        return mSource;
    }

    public State getDestiny() {
        return mDestiny;
    }

    public void setDestiny(State mDestiny) {this.mDestiny = mDestiny;}

    public String getColor() {
        return mColor;
    }

    public void setColor(String color) {
        mColor = color;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public String getTextColor() {
        return mTextColor;
    }

    public void setTextColor(String color) {
        mTextColor = color;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public int getTextSize() {
        return mTextSize;
    }

    public void setTextSize(int size) {
        mTextSize = size;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public String getTextStyle() {
        return mTextStyle;
    }

    public void setTextSyle(String color) {
        mTextStyle = color;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public Double getProbability() {
        return mProbability;
    }

    public void setProbability(Double probability) {
        mProbability = probability;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public String getGuard() {
        return mGuard;
    }

    public void setGuard(String guard) {
        mGuard = guard;
        for (Listener l : mListeners) {
            l.onChange(this);
        }
    }

    public int getPropertyBigState() {
        return propertyBigState;
    }

    public void setPropertyBigState(int propertyBigState) {
        this.propertyBigState = propertyBigState;
    }

    public int getmTransitionsStatesCount() { return mVisitedTransitionsCount; }

    public void setmTransitionsStatesCount(int value) { mVisitedTransitionsCount = value; }

    public void incrementTransitionsCount() { mVisitedTransitionsCount++; }

    public void decrementTransitionsCount() { mVisitedTransitionsCount--; }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.mSource);
        hash = 11 * hash + Objects.hashCode(this.mDestiny);
        hash = 11 * hash + Objects.hashCode(this.mLabel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transition other = (Transition) obj;
        if (!Objects.equals(this.mSource, other.mSource)) {
            return false;
        }
        if (!Objects.equals(this.mDestiny, other.mDestiny)) {
            return false;
        }
        if (!Objects.equals(this.mLabel, other.mLabel)) {
            return false;
        }

        if (!Objects.equals(this.mActions, other.mActions)) {
            return false;
        }

        if (!Objects.equals(this.mParameters, other.mParameters)) {
            return false;
        }
        if (!Objects.equals(this.mProbability, other.mProbability)) {
            return false;
        }
        return true;
    }

    public void addListener(Listener l) {
        mListeners.add(l);
    }

    public void removeListener(Listener l) {
        mListeners.remove(l);
    }

    public Object getValue(String key) {
        return mValues.get(key);
    }

    public void setValue(String key, Object value) {
        mValues.put(key, value);
    }

    @Override
    public Transition clone() throws CloneNotSupportedException {
        Transition t = new Transition(mSource, mDestiny);
        t.mColor = mColor;
        t.mGuard = mGuard;
        t.mLabel = mLabel;
        t.mActions = mActions;
        t.mParameters = mParameters;
        t.mProbability = mProbability;
        t.mTextColor = mTextColor;
        t.mTextSize = mTextSize;
        t.mTextStyle = mTextStyle;
        t.mWidth = mWidth;
        t.setValue("view.type", getValue("view.type"));
        return t;
    }

}