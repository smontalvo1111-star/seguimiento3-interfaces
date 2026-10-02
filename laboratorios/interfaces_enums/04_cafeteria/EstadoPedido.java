public enum EstadoPedido {
    RECIBIDO,
    EN_PREPARACION,
    LISTO,
    ENTREGADO,
    CANCELADO;

    public EstadoPedido siguiente() {
        return switch (this) {
            case RECIBIDO -> EN_PREPARACION;
            case EN_PREPARACION -> LISTO;
            case LISTO -> ENTREGADO;
            case ENTREGADO -> ENTREGADO;
            case CANCELADO -> CANCELADO;
        };
    }

    public EstadoPedido cancelar() {
        if (this == RECIBIDO || this == EN_PREPARACION) {
            return CANCELADO;
        }
        throw new IllegalStateException("No se puede cancelar en estado " + this);
    }
}
