/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;

/**
 *
 * @author Flash
 */
public class Tuple<X, Y> {
    private X x_;
    private Y y_;
    
    public Tuple(X x, Y y)
    {
        x_ = x;
        y_ = y;
    }
    
    public X getX()
    {
        return x_;
    }
    
    public Y getY()
    {
        return y_;
    }
    
    public void setX(X x)
    {
        x_ = x;
    }
    
    public void setY(Y y)
    {
        y_ = y;
    }
    
    
}
