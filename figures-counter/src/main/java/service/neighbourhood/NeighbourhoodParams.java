package service.neighbourhood;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class NeighbourhoodParams{
    Boolean decX, decY, incX, incY;
}