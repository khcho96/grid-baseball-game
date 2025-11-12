package dto;

public record SizeDto(int size) {

    public static SizeDto newInstance(int size) {
        return new SizeDto(size);
    }
}
