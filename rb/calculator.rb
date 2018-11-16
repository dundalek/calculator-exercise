class Calculator
  def initialize(p)
    @p = p.split("/").map { |x| Integer(x) }
  end

  def monomial_stringify(coefficient, exponent)
    if coefficient == 0 then
      ""
    else
      coefficient.to_s + case exponent
        when 0
          ""
        when 1
          "x"
        else
          "x^" + exponent.to_s
      end
    end
  end

  def differentiate
    degree = @p.length - 1
    @p = @p[0...-1].map.with_index do |coefficient, index|
      coefficient * (degree - index)
    end
    self
  end

  def to_s
    degree = @p.length - 1
    @p.map.with_index {|coefficient, index|
      s = monomial_stringify coefficient, (degree - index)
      if index > 0 and s != "" and coefficient > 0
        "+" + s
      else
        s
      end
    }.join("")
  end
end
